package ie.setu.cloudbalance_00

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingScreen()
        }
    }
}

@Composable
fun GreetingScreen() {
    var greetingText by remember { mutableStateOf("Welcome to Cloud Balance!") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = greetingText, style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { greetingText = "Hello, Developer!" }) {
            Text("Click Me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGreeting() {
    GreetingScreen()
}
