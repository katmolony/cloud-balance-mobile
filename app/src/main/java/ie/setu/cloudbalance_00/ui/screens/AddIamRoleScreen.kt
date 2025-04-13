package ie.setu.cloudbalance_00.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ie.setu.cloudbalance_00.network.RetrofitInstance
import ie.setu.cloudbalance_00.util.SecureStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AddIamRoleScreen() {
    val context = LocalContext.current
    var roleArn by remember { mutableStateOf("") }
    var responseMessage by remember { mutableStateOf<String?>(null) }

    val token = SecureStorage.getAccessToken(context)
    val userId = SecureStorage.getUserId(context)

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Enter your IAM Role ARN", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = roleArn,
            onValueChange = { roleArn = it },
            label = { Text("IAM Role ARN") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (token != null && userId != null && roleArn.isNotBlank()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val body = mapOf(
                                "role_arn" to roleArn,
                                "external_id" to "cloud-balance-dev"
                            )
                            val response = RetrofitInstance.api.fetchAwsForUser(userId, body)
                            withContext(Dispatchers.Main) {
                                responseMessage = "✅ ${response.message}"
                            }
                        } catch (e: Exception) {
                            Log.e("AddIamRoleScreen", "❌ API call failed", e)
                            withContext(Dispatchers.Main) {
                                responseMessage = "❌ Failed: ${e.message}"
                            }
                        }
                    }
                } else {
                    responseMessage = "❗ Missing role ARN or credentials"
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Submit Role")
        }

        responseMessage?.let {
            Spacer(modifier = Modifier.height(24.dp))
            Text(it)
        }
    }
}
