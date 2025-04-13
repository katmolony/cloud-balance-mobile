package ie.setu.cloudbalance_00.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ie.setu.cloudbalance_00.network.AwsCost
import ie.setu.cloudbalance_00.network.UserResponse
import ie.setu.cloudbalance_00.network.RetrofitInstance
import ie.setu.cloudbalance_00.util.SecureStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen( onNavigateToAddIamRole: () -> Unit) {
    val context = LocalContext.current
    var users by remember { mutableStateOf<List<UserResponse>>(emptyList()) }
//    var costs by remember { mutableStateOf<List<AwsCost>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val token = SecureStorage.getAccessToken(context)
    val isTokenValid = !token.isNullOrBlank()

    LaunchedEffect(isTokenValid) {
        if (!isTokenValid) {
            errorMessage = "❌ Missing access token"
            return@LaunchedEffect
        }

        try {
            val usersResponse = withContext(Dispatchers.IO) {
                RetrofitInstance.api.getAllUsers()
            }
            users = usersResponse.users

//            val costResponse = withContext(Dispatchers.IO) {
//                RetrofitInstance.api.getAwsCostsByUserId(
//                    userId = 1,
//                    token = "Bearer $token"
//                )
//            }
//            costs = costResponse.costs
//            errorMessage = null

        } catch (e: Exception) {
            Log.e("HomeScreen", "❌ Failed to fetch data", e)
            errorMessage = "❌ Failed to load data: ${e.message ?: "Unknown error"}"
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Welcome to the Home Screen!", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))

        errorMessage?.let {
            Text(it)
            return@Column
        }

        Text("Users:", style = MaterialTheme.typography.h6)
        if (users.isEmpty()) {
            Text("Loading users...")
        } else {
            users.forEach {
                Text("${it.name} - ${it.email}")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("AWS Cost Data:", style = MaterialTheme.typography.h6)
//        if (costs.isEmpty()) {
//            Text("No cost data available.")
//        } else {
//            costs.forEach { cost ->
//                Text("${cost.period_start.take(10)} — \$${cost.cost} ${cost.currency}")
//            }
//        }
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onNavigateToAddIamRole) {
            Text("Add IAM Role")
        }
    }
}
