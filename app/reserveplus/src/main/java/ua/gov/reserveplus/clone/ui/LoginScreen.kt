package ua.gov.reserveplus.clone.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import ua.gov.reserveplus.design.Caption
import ua.gov.reserveplus.design.H1
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.Paper
import ua.gov.reserveplus.design.ReserveTheme
import ua.gov.reserveplus.design.Sand
import ua.gov.reserveplus.design.Space
import ua.gov.reserveplus.design.R as DRes

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    var code by remember { mutableStateOf("") }

    LaunchedEffect(code) {
        if (code.length == 4) {
            delay(180)
            onLogin()
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Sand)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = Space.xl)
    ) {
        Spacer(Modifier.height(60.dp))
        Text("Код для входу", style = H1.copy(fontSize = 32.sp), color = Ink)
        Spacer(Modifier.height(80.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(4) { index ->
                Box(
                    Modifier
                        .size(9.dp)
                        .background(
                            if (index < code.length) Paper else Paper.copy(alpha = 0.45f),
                            CircleShape
                        )
                )
                if (index < 3) Spacer(Modifier.size(50.dp))
            }
        }

        Spacer(Modifier.height(130.dp))

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Keypad(
                onDigit = { if (code.length < 4) code += it },
                onBack = { if (code.isNotEmpty()) code = code.dropLast(1) }
            )
        }

        Spacer(Modifier.height(Space.xxl))
        Text(
            "Не пам'ятаю код для входу",
            style = Caption.copy(fontSize = 15.sp),
            color = Ink,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.weight(1f))
    }
}

@Composable
private fun Keypad(onDigit: (String) -> Unit, onBack: () -> Unit) {
    val rows = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9")
    )
    rows.forEach { row ->
        Row(horizontalArrangement = Arrangement.spacedBy(27.dp)) {
            row.forEach { digit -> Key(digit, onDigit) }
        }
        Spacer(Modifier.height(16.dp))
    }
    Row(horizontalArrangement = Arrangement.spacedBy(27.dp)) {
        Box(
            Modifier.size(75.dp).clickable { },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(DRes.drawable.fingerprint_dialog_fp_icon),
                contentDescription = null,
                tint = Paper,
                modifier = Modifier.size(38.dp)
            )
        }
        Key("0", onDigit)
        Box(
            Modifier.size(75.dp).clickable { onBack() },
            contentAlignment = Alignment.Center
        ) {
            BackspaceIcon()
        }
    }
}

@Composable
private fun Key(digit: String, onDigit: (String) -> Unit) {
    Box(
        Modifier
            .size(75.dp)
            .background(Paper, CircleShape)
            .clickable { onDigit(digit) },
        contentAlignment = Alignment.Center
    ) {
        Text(digit, style = H1.copy(fontSize = 28.sp), color = Ink)
    }
}

@Composable
private fun BackspaceIcon() {
    Canvas(Modifier.size(58.dp)) {
        val w = size.width
        val h = size.height
        val p = Path().apply {
            moveTo(w * 0.38f, h * 0.24f)
            lineTo(w * 0.92f, h * 0.24f)
            lineTo(w * 0.92f, h * 0.76f)
            lineTo(w * 0.38f, h * 0.76f)
            lineTo(w * 0.08f, h * 0.5f)
            close()
        }
        drawPath(p, Paper)
        drawLine(Ink, Offset(w * 0.5f, h * 0.4f), Offset(w * 0.7f, h * 0.6f), w * 0.075f, StrokeCap.Round)
        drawLine(Ink, Offset(w * 0.7f, h * 0.4f), Offset(w * 0.5f, h * 0.6f), w * 0.075f, StrokeCap.Round)
    }
}

@Preview(device = "spec:width=1080px,height=2400px,dpi=420")
@Composable
private fun LoginPreview() {
    ReserveTheme { LoginScreen(onLogin = {}) }
}