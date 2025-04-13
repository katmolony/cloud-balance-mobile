package ie.setu.cloudbalance_00.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ie.setu.cloudbalance_00.viewmodel.AuthState
import ie.setu.cloudbalance_00.viewmodel.AuthViewModel

@Composable
fun ConfirmSignupScreen(
    authViewModel: AuthViewModel,
    onConfirmSuccess: () -> Unit,
    onBackToLogin: () -> Unit,
    prefilledEmail: String = "" // 👈 add this
)
 {
    val authState by authViewModel.authState.collectAsState()
     var email by remember { mutableStateOf(prefilledEmail) }
     var code by remember { mutableStateOf("") }

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.SignUpSuccess -> onConfirmSuccess()
            is AuthState.Error -> {

            }
            else -> Unit
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Confirm Your Account", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        OutlinedTextField(
            value = code,
            onValueChange = { code = it },
            label = { Text("Verification Code") }
        )

        Button(
            onClick = {
                authViewModel.confirmSignUp(email, code)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Confirm")
        }

        TextButton(onClick = onBackToLogin) {
            Text("Back to Login")
        }
    }
}
