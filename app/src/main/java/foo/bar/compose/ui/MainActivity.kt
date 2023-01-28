package foo.bar.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import foo.bar.compose.ui.screens.home.HomeScreen
import foo.bar.compose.ui.size.rememberWindowSize
import foo.bar.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTheme {
                Surface( modifier = Modifier.fillMaxSize()) {
                    HomeScreen(rememberWindowSize())
                }
            }
        }
    }
}
