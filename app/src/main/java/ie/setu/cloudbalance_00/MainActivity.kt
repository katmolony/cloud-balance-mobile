package ie.setu.cloudbalance_00

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import ie.setu.cloudbalance_00.ui.navigation.NavGraph
import ie.setu.cloudbalance_00.viewmodel.AuthViewModel
import ie.setu.cloudbalance_00.viewmodel.AuthViewModelFactory
import androidx.compose.ui.platform.LocalContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current.applicationContext
            val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(context))
            NavGraph(authViewModel = authViewModel)
        }
    }
}
