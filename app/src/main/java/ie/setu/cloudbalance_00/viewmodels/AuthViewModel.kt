package ie.setu.cloudbalance_00.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import aws.sdk.kotlin.services.cognitoidentityprovider.CognitoIdentityProviderClient
import aws.sdk.kotlin.services.cognitoidentityprovider.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthViewModel : ViewModel() {

    private val clientId = "4mit5t5og0tvj8a72guvel3iqu"
    private val region = "us-east-1"

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    CognitoIdentityProviderClient {
                        region = this@AuthViewModel.region
                    }.use { client ->
                        val request = SignUpRequest {
                            clientId = this@AuthViewModel.clientId
                            username = email
                            this.password = password
                            userAttributes = listOf(
                                AttributeType {
                                    name = "email"
                                    value = email
                                }
                            )
                        }

                        val response = client.signUp(request)
                        Log.d("AuthViewModel", "✅ SignUp success: confirmed = ${response.userConfirmed}")

                        _authState.value = if (response.userConfirmed == true)
                            AuthState.SignUpSuccess
                        else
                            AuthState.ConfirmSignUpRequired(email)
                    }
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "❌ SignUp failed", e)
                _authState.value = AuthState.Error(e.message ?: "Signup failed")
            }
        }
    }

    fun confirmSignUp(email: String, code: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    CognitoIdentityProviderClient {
                        this.region = this@AuthViewModel.region
                    }.use { client ->
                        val request = ConfirmSignUpRequest {
                            this.clientId = clientId
                            this.username = email
                            this.confirmationCode = code
                        }

                        client.confirmSignUp(request)
                        Log.d("AuthViewModel", "✅ Confirmed sign up for $email")
                        _authState.value = AuthState.SignUpSuccess
                    }
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "❌ ConfirmSignUp failed", e)
                _authState.value = AuthState.Error(e.message ?: "Confirmation failed")
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                withContext(Dispatchers.IO) {
                    CognitoIdentityProviderClient {
                        this.region = this@AuthViewModel.region
                    }.use { client ->
                        val request = InitiateAuthRequest {
                            this.authFlow = AuthFlowType.UserPasswordAuth
                            this.clientId = this@AuthViewModel.clientId
                            this.authParameters = mapOf(
                                "USERNAME" to email,
                                "PASSWORD" to password
                            )
                        }

                        val response = client.initiateAuth(request)
                        if (response.authenticationResult != null) {
                            Log.d("AuthViewModel", "✅ Login successful for $email")
                            _authState.value = AuthState.LoginSuccess
                        } else {
                            Log.d("AuthViewModel", "⚠️ Login incomplete, needs confirmation")
                            _authState.value = AuthState.Error("Login incomplete. Try confirming your account first.")
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "❌ Login failed", e)
                _authState.value = AuthState.Error(e.message ?: "Login failed")
            }
        }
    }

    fun resetState() {
        _authState.value = AuthState.Idle
    }
}