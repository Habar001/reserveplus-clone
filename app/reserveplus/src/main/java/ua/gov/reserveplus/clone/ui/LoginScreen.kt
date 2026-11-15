package ua.gov.reserveplus.clone.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.gov.reserveplus.design.BodySmall
import ua.gov.reserveplus.design.H1
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.InkMuted
import ua.gov.reserveplus.design.ReserveTheme
import ua.gov.reserveplus.design.Sand
import ua.gov.reserveplus.design.Space
import ua.gov.reserveplus.design.components.HeaderBar
import ua.gov.reserveplus.design.components.OutlineButton
import ua.gov.reserveplus.design.components.StatusCircle
import ua.gov.reserveplus.design.R as DRes

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Sand)
            .statusBarsPadding()
    ) {
        HeaderBar(
            menuIcon = painterResource(DRes.drawable.ic_hamburger),
            onMenu = {}
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = Space.screen),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            StatusCircle(painterResource(DRes.drawable.ic_trident))
            Spacer(Modifier.height(Space.xxxl))
            Text("Авторизація", style = H1, color = Ink, textAlign = TextAlign.Center)
            Spacer(Modifier.height(Space.l))
            Text(
                "Увійдіть, щоб переглянути військово-обліковий документ",
                style = BodySmall,
                color = InkMuted,
                textAlign = TextAlign.Center
            )
        }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = Space.screen)
                .navigationBarsPadding()
                .padding(bottom = Space.xl)
        ) {
            OutlineButton("BankID НБУ", onClick = onLogin)
            Spacer(Modifier.height(Space.l))
            OutlineButton("Дія", onClick = onLogin)
        }
    }
}

@Preview(device = "spec:width=1080px,height=2400px,dpi=420")
@Composable
private fun LoginPreview() {
    ReserveTheme { LoginScreen {} }
}