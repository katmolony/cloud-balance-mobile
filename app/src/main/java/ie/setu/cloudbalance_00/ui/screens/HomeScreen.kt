package ie.setu.cloudbalance_00.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ie.setu.cloudbalance_00.network.AwsCost
import ie.setu.cloudbalance_00.network.AwsResource
import ie.setu.cloudbalance_00.network.UserResponse
import ie.setu.cloudbalance_00.network.RetrofitInstance
import ie.setu.cloudbalance_00.util.SecureStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

@Composable
fun HomeScreen(
    onNavigateToAddIamRole: () -> Unit,
    onNavigateToIamRoleGuide: () -> Unit
) {
    val context = LocalContext.current
    var users by remember { mutableStateOf<List<UserResponse>>(emptyList()) }
    var costs by remember { mutableStateOf<List<AwsCost>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var userHasNoIamRole by remember { mutableStateOf(false) }
    var resources by remember { mutableStateOf<List<AwsResource>>(emptyList()) }


    val userId = SecureStorage.getUserId(context)
    val token = SecureStorage.getAccessToken(context)
    val isTokenValid = !token.isNullOrBlank()

    LaunchedEffect(userId, token) {
        if (userId == null || token.isNullOrBlank()) {
            errorMessage = "❌ Missing access token or user ID"
            return@LaunchedEffect
        }
        try {
            withContext(Dispatchers.IO) {
                RetrofitInstance.api.getIamRoleByUserId(userId)
            }
            userHasNoIamRole = false
        } catch (e: HttpException) {
            if (e.code() == 404) {
                userHasNoIamRole = true
                Log.d("HomeScreen", "ℹ️ No IAM Role found for user $userId")
            } else {
                Log.e("HomeScreen", "❌ IAM Role check failed", e)
            }
        }

        try {

            val costResponse = withContext(Dispatchers.IO) {
                RetrofitInstance.api.getAwsCostsByUserId(
                    userId = userId
                )
            }
            costs = costResponse.costs
            errorMessage = null

        } catch (e: Exception) {
            Log.e("HomeScreen", "❌ Failed to fetch data", e)
            errorMessage = "❌ Failed to load data: ${e.message ?: "Unknown error"}"
        }

        val resourcesResponse = withContext(Dispatchers.IO) {
            RetrofitInstance.api.getAwsResourcesByUserId(
                userId = userId
            )
        }
        resources = resourcesResponse.resources
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Welcome to the Home Screen!", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage != null) {
            Text(errorMessage!!)
            return
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("AWS Cost Data:", style = MaterialTheme.typography.h6)

        if (costs.isEmpty()) {
            Text("No cost data available.")
        } else {
            val total = costs.sumOf { it.cost.toDoubleOrNull() ?: 0.0 }
            Text("Total: \$${String.format("%.2f", total)} USD")

            Spacer(modifier = Modifier.height(8.dp))
            costs.forEach { cost ->
                Text("${cost.period_start.take(10)} — \$${cost.cost} ${cost.currency}")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text("AWS Resource Data:", style = MaterialTheme.typography.h6)
        if (resources.isEmpty()) {
            Text("No resource data available.")
        } else {
            resources.forEach { res ->
                Text("${res.service_name} (${res.region}): ${res.resource_id}")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (userHasNoIamRole) {
            Button(onClick = onNavigateToIamRoleGuide) {
                Text("Setup IAM Role")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onNavigateToAddIamRole) {
            Text("Add IAM Role")
        }
    }
}
