package ie.setu.cloudbalance_00.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ie.setu.cloudbalance_00.network.RetrofitInstance
import ie.setu.cloudbalance_00.network.UserResponse
import ie.setu.cloudbalance_00.util.SecureStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    var users by remember { mutableStateOf<List<UserResponse>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val token = SecureStorage.getAccessToken(context)
        Log.d("HomeScreen", "Retrieved token: ${token?.take(20)}...") // log first part only

        if (token != null) {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.api.getAllUsers()
                }
                Log.d("HomeScreen", "✅ Fetched users: ${response.users.size}")
                users = response.users
                errorMessage = null
            } catch (e: Exception) {
                Log.e("HomeScreen", "❌ Failed to fetch users", e)
                errorMessage = "❌ Failed to load users: ${e.localizedMessage}"
            }
        } else {
            Log.w("HomeScreen", "❌ No access token found")
            errorMessage = "❌ No access token found"
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Welcome to the Home Screen!", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))

        when {
            errorMessage != null -> Text(errorMessage ?: "")
            users.isEmpty() -> Text("Loading users...")
            else -> users.forEach {
                Text("${it.name} - ${it.email}")
            }
        }
    }
}
