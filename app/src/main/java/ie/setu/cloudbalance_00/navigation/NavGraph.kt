package ie.setu.cloudbalance_00.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ie.setu.cloudbalance_00.navigation.NavRoutes
import ie.setu.cloudbalance_00.ui.screens.*
import ie.setu.cloudbalance_00.viewmodel.AuthViewModel

@Composable
fun NavGraph(
    startDestination: String = "login",
    authViewModel: AuthViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToSignup = { navController.navigate("signup") },
                onLoginSuccess = { navController.navigate("home") }
            )
        }

        composable("signup") {
            SignupScreen(
                authViewModel = authViewModel,
                onSignupSuccess = { navController.navigate("login") },
                onBackToLogin = { navController.popBackStack("login", inclusive = false) },
                onConfirmRequired = { email ->
                    navController.navigate("confirm?email=${email}")
                }
            )
        }

        composable("confirm?email={email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            ConfirmSignupScreen(
                authViewModel = authViewModel,
                onConfirmSuccess = { navController.navigate("login") },
                onBackToLogin = { navController.popBackStack("login", inclusive = false) },
                prefilledEmail = email // 👈 new
            )
        }

        composable(NavRoutes.Home.route) {
            HomeScreen(
                onNavigateToAddIamRole = { navController.navigate(NavRoutes.AddIamRole.route) }
            )
        }
        composable(NavRoutes.AddIamRole.route) {
            AddIamRoleScreen()
        }
    }
}
