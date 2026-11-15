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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.gov.reserveplus.design.BodySmall
import ua.gov.reserveplus.design.H1
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.InkMuted
import ua.gov.reserveplus.design.LineSoft
import ua.gov.reserveplus.design.ReserveTheme
import ua.gov.reserveplus.design.Sand
import ua.gov.reserveplus.design.Space
import ua.gov.reserveplus.design.components.PrimaryButton
import ua.gov.reserveplus.design.components.RadioRow

private val violations = listOf(
    "Не з'явився за повісткою",
    "Не стали на військовий облік за новою адресою",
    "Не пройшли ВЛК",
    "Не стали на облік за місцем перебування як ВПО",
    "Не перебували на обліку за місцем проживання",
    "Не надали майно під час мобілізації"
)

@Composable
fun FinesScreen(onBack: () -> Unit, onCreate: () -> Unit) {
    var selected by remember { mutableStateOf<String?>(null) }

    Column(
        Modifier
            .fillMaxSize()
            .background(Sand)
            .statusBarsPadding()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = Space.screen, vertical = Space.m),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackArrow { onBack() }
            Spacer(Modifier.weight(1f))
            HelpDot()
        }

        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Space.screen)
                .navigationBarsPadding()
        ) {
            Text("Штрафи", style = H1.copy(fontSize = 28.sp), color = Ink)
            Spacer(Modifier.height(Space.m))
            Text(
                "Виберіть порушення, щоб створити заяву",
                style = BodySmall,
                color = InkMuted
            )
            Spacer(Modifier.height(Space.xl))

            violations.forEach { item ->
                RadioRow(
                    title = item,
                    selected = selected == item,
                    onClick = { selected = item }
                )
                Spacer(Modifier.height(Space.m))
            }

            Spacer(Modifier.height(Space.l))
        }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = Space.screen)
                .navigationBarsPadding()
                .padding(bottom = Space.xl)
        ) {
            PrimaryButton("Створити заяву", enabled = selected != null, onClick = onCreate)
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

@Composable
private fun HelpDot() {
    Row(
        Modifier
            .size(26.dp)
            .background(LineSoft, CircleShape),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("?", color = Color.White, fontSize = 13.sp)
    }
}

@Preview(device = "spec:width=1080px,height=2400px,dpi=420")
@Composable
private fun FinesPreview() {
    ReserveTheme { FinesScreen({}, {}) }
}