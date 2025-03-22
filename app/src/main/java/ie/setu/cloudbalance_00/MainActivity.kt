package ie.setu.cloudbalance_00

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.navigation.compose.rememberNavController
import ie.setu.cloudbalance_00.navigation.AppNavHost
import ie.setu.cloudbalance_00.ui.theme.CloudBalanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CloudBalanceTheme {
                val navController = rememberNavController()
                AppNavHost(navController)
            }
        }
    }
}
