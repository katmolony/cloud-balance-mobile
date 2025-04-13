package ie.setu.cloudbalance_00.navigation

sealed class NavRoutes(val route: String) {
    object Login : NavRoutes("login")
    object Home : NavRoutes("home")
    object AddIamRole : NavRoutes("add_iam_role")
    object IamRoleGuide : NavRoutes("iamRoleGuide")

}
