package ua.gov.reserveplus.clone

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ua.gov.reserveplus.clone.ui.DefermentScreen
import ua.gov.reserveplus.clone.ui.FinesScreen
import ua.gov.reserveplus.clone.ui.LoginScreen
import ua.gov.reserveplus.clone.ui.MainScreen
import ua.gov.reserveplus.clone.ui.MenuScreen
import ua.gov.reserveplus.clone.ui.ServicesScreen
import ua.gov.reserveplus.clone.ui.VacanciesScreen
import ua.gov.reserveplus.design.components.BottomNav
import ua.gov.reserveplus.design.ReserveTheme
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
            isAppearanceLightNavigationBars = true
        }
        setContent {
            ReserveTheme {
                var screen by remember { mutableStateOf("login") }
                var tab by remember { mutableStateOf("id") }
                val showBar = screen == "main" || screen == "services" ||
                    screen == "vacancies" || screen == "menu"

                Column(Modifier.fillMaxSize()) {
                    Box(Modifier.weight(1f)) {
                        when (screen) {
                            "login" -> LoginScreen(onLogin = { screen = "main" })
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
                            "vacancies" -> VacanciesScreen()
                            "menu" -> MenuScreen()
                            "deferment" -> DefermentScreen(
                                onBack = { screen = "services" },
                                onNext = { screen = "main" }
                            )
                            "fines" -> FinesScreen(
                                onBack = { screen = "services" },
                                onCreate = { screen = "main" }
                            )
                            else -> MainScreen(
                                onOpenQr = { screen = "main" }
                            )
                        }
                    }
                    if (showBar) {
                        BottomNav(selected = tab) { id ->
                            tab = id
                            screen = when (id) {
                                "id" -> "main"
                                "services" -> "services"
                                "vacancies" -> "vacancies"
                                else -> "menu"
                            }
                        }
                    }
                }
            }
        }
    }
}