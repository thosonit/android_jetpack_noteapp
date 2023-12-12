import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import it.thoson.note.ui.theme.AppTheme

@Composable
fun SplashScreen(
    navController: NavHostController
) {
    val handler = remember { Handler(Looper.getMainLooper()) }
    LaunchedEffect(true) {
        Log.d("SplashScreen", "LaunchedEffect")
        handler.postDelayed({
            Log.d("SplashScreen", "navigate")
            navController.navigate("home_screen") {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
            }
        }, 300) // 2000 milliseconds (2 seconds) delay for the splash screen
    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {}
        },
    )
}

@Preview
@Composable
fun SplashScreenPreview() {
    val navController = rememberNavController()
    AppTheme {
        SplashScreen(navController = navController)
    }
}