package ie.setu.cloudbalance_00.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ie.setu.cloudbalance_00.viewmodel.AuthState
import ie.setu.cloudbalance_00.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onLoginSuccess: () -> Unit,
    onNavigateToSignup: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val authState by authViewModel.authState.collectAsState()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.LoginSuccess -> onLoginSuccess()
            is AuthState.Error -> {
                val error = (authState as AuthState.Error).message
                scaffoldState.snackbarHostState.showSnackbar(error)
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
            Text("Login to Cloud Balance", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Email") }
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") }
            )

            Button(
                onClick = { authViewModel.login(username, password) },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Log In")
            }

            TextButton(
                onClick = onNavigateToSignup,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Don’t have an account? Sign Up")
            }
        }
    }
}


