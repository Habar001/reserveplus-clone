package ua.gov.reserveplus.clone.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.gov.reserveplus.design.H1
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.ReserveTheme
import ua.gov.reserveplus.design.Sand
import ua.gov.reserveplus.design.Space
import ua.gov.reserveplus.design.components.PrimaryButton
import ua.gov.reserveplus.design.components.RadioRow
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import ua.gov.reserveplus.design.LineSoft

private data class Reason(val title: String, val subtitle: String? = null)

private val reasons = listOf(
    Reason("Людина з інвалідністю"),
    Reason("Студент, аспірант"),
    Reason("Батьки 3+ дітей", "Якщо ви у шлюбі та маєте від трьох неповнолітніх дітей"),
    Reason("Сім'я захисника або захисниці з дитиною"),
    Reason("За станом здоров'я"),
    Reason("Науковий працівник")
)

@Composable
fun DefermentScreen(onBack: () -> Unit, onNext: () -> Unit) {
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
            Text(
                "Виберіть підставу\nдля відстрочки",
                style = H1.copy(fontSize = 26.sp, lineHeight = 30.sp),
                color = Ink
            )
            Spacer(Modifier.height(Space.l))

            reasons.forEach { reason ->
                RadioRow(
                    title = reason.title,
                    subtitle = if (selected == reason.title) reason.subtitle else null,
                    selected = selected == reason.title,
                    onClick = { selected = reason.title }
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
            PrimaryButton("Далі", enabled = selected != null, onClick = onNext)
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
    Box(
        Modifier
            .size(26.dp)
            .background(LineSoft, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text("?", color = Color.White, fontSize = 13.sp)
    }
}