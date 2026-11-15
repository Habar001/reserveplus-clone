package ua.gov.reserveplus.clone

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ua.gov.reserveplus.clone.ui.DefermentScreen
import ua.gov.reserveplus.clone.ui.DocumentScreen
import ua.gov.reserveplus.clone.ui.FinesScreen
import ua.gov.reserveplus.clone.ui.LoginScreen
import ua.gov.reserveplus.clone.ui.MainScreen
import ua.gov.reserveplus.clone.ui.ServicesScreen
import ua.gov.reserveplus.design.ReserveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        setContent {
            ReserveTheme {
                var screen by remember { mutableStateOf("login") }
                when (screen) {
                    "login" -> LoginScreen(onLogin = { screen = "main" })
                    "document" -> DocumentScreen(onBack = { screen = "main" })
                    "services" -> ServicesScreen(
                        onBack = { screen = "main" },
                        onOpen = { route ->
                            when (route) {
                                "deferment" -> screen = "deferment"
                                "fines" -> screen = "fines"
                                else -> Unit
                            }
                        }
                    )
                    "deferment" -> DefermentScreen(
                        onBack = { screen = "services" },
                        onNext = { screen = "main" }
                    )
                    "fines" -> FinesScreen(
                        onBack = { screen = "services" },
                        onCreate = { screen = "main" }
                    )
                    else -> MainScreen(
                        onOpenDocument = { screen = "document" },
                        onOpenServices = { screen = "services" },
                        onOpenService = { route ->
                            when (route) {
                                "deferment" -> screen = "deferment"
                                "fines" -> screen = "fines"
                                else -> screen = "services"
                            }
                        }
                    )
                }
            }
        }
    }
}