package ie.setu.cloudbalance_00.viewmodel

sealed class AuthState {
    object Idle : AuthState()
    object SignUpSuccess : AuthState()
    data class ConfirmSignUpRequired(val email: String) : AuthState()
    object LoginSuccess : AuthState()

    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    object SignedUp : AuthState()
    data class Error(val message: String) : AuthState()
}
