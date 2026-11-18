package ua.gov.reserveplus.clone.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.gov.reserveplus.clone.data.Profile
import ua.gov.reserveplus.clone.ui.components.QrCode
import ua.gov.reserveplus.design.Caption
import ua.gov.reserveplus.design.H3
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.InkMuted
import ua.gov.reserveplus.design.Radius
import ua.gov.reserveplus.design.ReserveTheme
import ua.gov.reserveplus.design.Sand
import ua.gov.reserveplus.design.SandSunken
import ua.gov.reserveplus.design.Space

@Composable
fun QrScreen(onBack: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Sand)
            .statusBarsPadding()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = Space.screen),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackArrow(onBack)
        }

        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Space.screen),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "QR-код дійсний до 22 червень 2027",
                style = H3,
                color = Ink,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(Space.xxl))
            QrCode(content = Profile.QR_PAYLOAD, size = 240.dp)
            Spacer(Modifier.height(Space.xxl))
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(SandSunken, RoundedCornerShape(Radius.m))
                    .padding(Space.xl)
            ) {
                Text(
                    "Використовуйте QR-код лише за потреби та відповідально ставтеся до його поширення.",
                    style = Caption,
                    color = InkMuted
                )
            }
            Spacer(Modifier.height(Space.xxl))
        }
    }
}

@Composable
private fun BackArrow(onClick: () -> Unit) {
    Canvas(Modifier.size(24.dp).clickable { onClick() }) {
        val w = size.width
        val h = size.height
        val p = Path().apply {
            moveTo(w * 0.62f, h * 0.22f)
            lineTo(w * 0.34f, h * 0.5f)
            lineTo(w * 0.62f, h * 0.78f)
        }
        drawPath(p, Ink, style = Stroke(width = w * 0.08f, cap = StrokeCap.Round, join = StrokeJoin.Round))
    }
}

@Preview(device = "spec:width=1080px,height=2400px,dpi=420")
@Composable
private fun QrPreview() {
    ReserveTheme { QrScreen {} }
}