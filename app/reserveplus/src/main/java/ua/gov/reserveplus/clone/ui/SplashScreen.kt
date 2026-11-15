package ua.gov.reserveplus.clone.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ua.gov.reserveplus.design.Caption
import ua.gov.reserveplus.design.EUkraine
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.R
import ua.gov.reserveplus.design.Sand
import ua.gov.reserveplus.design.Space

@Composable
fun SplashScreen(onDone: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(1200)
        onDone()
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(Ink),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.ic_min_oborony_logo),
                contentDescription = null,
                modifier = Modifier.size(96.dp)
            )
            Spacer(Modifier.height(Space.xxxl))
            Text("Резерв+", style = Caption.copy(fontFamily = EUkraine), color = Sand)
        }
    }
}

@Preview(device = "spec:width=1080px,height=2400px,dpi=420")
@Composable
private fun SplashPreview() {
    SplashScreen {}
}