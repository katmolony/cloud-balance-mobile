package ie.setu.cloudbalance_00.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ie.setu.cloudbalance_00.viewmodel.AuthViewModel

class AuthViewModelFactory(private val appContext: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(appContext) as T
    }
}
