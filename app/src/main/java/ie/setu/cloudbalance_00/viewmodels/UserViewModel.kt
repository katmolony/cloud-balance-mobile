package ie.setu.cloudbalance_00.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ie.setu.cloudbalance_00.network.UserResponse
import ie.setu.cloudbalance_00.network.RetrofitInstance
import ie.setu.cloudbalance_00.util.SecureStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _users = MutableStateFlow<List<UserResponse>>(emptyList())
    val users: StateFlow<List<UserResponse>> = _users

    fun fetchUsers(context: Context) {
        viewModelScope.launch {
            try {
                val token = SecureStorage.getAccessToken(context)
                if (token != null) {
                    val result = RetrofitInstance.api.getAllUsers()
                    _users.value = result.users
                } else {
                    println("No token found")
                }
            } catch (e: Exception) {
                println("Error fetching users: ${e.message}")
            }
        }
    }
}
