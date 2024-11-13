package com.bytelogs.infosysscreening

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.Toast
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bytelogs.infosysscreening.ui.theme.InfosysScreeningTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InfosysScreeningTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("main") {
            MainScreen(navController)
        }
        composable("webview") {
            GoogleWebview()
        }
        composable("image") {
            ShowImage()
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.navigate("main") {
            popUpTo("splash") { inclusive = true }
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Splash Screen", fontSize = 24.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Infosys Screening") },
                modifier = Modifier.background(MaterialTheme.colorScheme.primary) // Modifier applied properly
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        Toast.makeText(context, "Button1 is clicked", Toast.LENGTH_LONG).show()
                    }) {
                        Text(text = "Show Toast")
                    }
                    Button(onClick = {
                        navController.navigate("webview") {
                            popUpTo("main")
                        }
                    }) {
                        Text(text = "Launch Webview")
                    }
                    Button(onClick = {
                        navController.navigate("image") {
                            popUpTo("main")
                        }
                    }) {
                        Text(text = "Show Image")
                    }
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GoogleWebview() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Google Webview") },
                modifier = Modifier.background(MaterialTheme.colorScheme.primary) // Modifier applied properly
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()) {
                AndroidView(modifier = Modifier.fillMaxSize(), factory = { context ->
                    WebView(context).apply {
                        settings.javaScriptEnabled = true
                        webViewClient = WebViewClient()
                        loadUrl("https://www.google.com")
                    }
                })
            }
        },
    )


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowImage() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Image") },
                modifier = Modifier.background(MaterialTheme.colorScheme.primary)
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)){
                    Image(
                        painter = painterResource(id = R.drawable.infosys_logo),
                        contentDescription = stringResource(id = R.string.infosys_logo)
                    )
                }
            }
        },
    )

}