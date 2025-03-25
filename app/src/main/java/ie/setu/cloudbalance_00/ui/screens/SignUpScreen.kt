package ie.setu.cloudbalance_00.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ie.setu.cloudbalance_00.viewmodel.AuthState
import ie.setu.cloudbalance_00.viewmodel.AuthViewModel

@Composable
fun SignupScreen(
    authViewModel: AuthViewModel,
    onSignupSuccess: () -> Unit,
    onBackToLogin: () -> Unit,
    onConfirmRequired: (email: String) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val authState by authViewModel.authState.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(authState) {
        println("SignupScreen AuthState = $authState") // Debug log

        when (authState) {
            is AuthState.SignUpSuccess -> {
                println("✅ SignUpSuccess - Navigating back to login")
                onSignupSuccess()
            }
            is AuthState.ConfirmSignUpRequired -> {
                val e = (authState as AuthState.ConfirmSignUpRequired).email
                println("⚠️ Confirmation Required for $e")
                onConfirmRequired(e)
            }
            is AuthState.Error -> {
                scaffoldState.snackbarHostState
                    .showSnackbar((authState as AuthState.Error).message)
                println("❌ Error - ${(authState as AuthState.Error).message}")
                authViewModel.resetState()
            }
            else -> Unit
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        if (authState is AuthState.Loading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Text("Create an Account", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") }
            )

            Button(
                onClick = {
                    println("📩 Sign Up button pressed with: $email / $password")
                    authViewModel.signUp(email, password)
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Sign Up")
            }

            TextButton(onClick = onBackToLogin) {
                Text("Back to Login")
            }
        }
    }
}
