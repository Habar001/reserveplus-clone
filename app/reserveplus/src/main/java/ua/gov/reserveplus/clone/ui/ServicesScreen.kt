package ua.gov.reserveplus.clone.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import ua.gov.reserveplus.design.Body
import ua.gov.reserveplus.design.H1
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.InkMuted
import ua.gov.reserveplus.design.LineSoft
import ua.gov.reserveplus.design.Radius
import ua.gov.reserveplus.design.ReserveTheme
import ua.gov.reserveplus.design.Sand
import ua.gov.reserveplus.design.Space
import ua.gov.reserveplus.design.R as DRes
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color

private data class Service(val title: String, val route: String)

private val services = listOf(
    Service("Виправити дані онлайн", "fix"),
    Service("Стати на облік", "enlist"),
    Service("Направлення на ВЛК", "vlk"),
    Service("Відстрочка", "deferment"),
    Service("Штрафи", "fines"),
    Service("Електронна черга до ТЦК", "queue"),
    Service("Вакансії до Сил оборони", "jobs")
)

@Composable
fun ServicesScreen(onBack: () -> Unit, onOpen: (String) -> Unit) {
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
            HelpCircle()
        }

        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Space.screen)
                .navigationBarsPadding()
        ) {
            Text("Сервіси", style = H1.copy(fontSize = 30.sp), color = Ink)
            Spacer(Modifier.height(Space.xl))

            services.forEachIndexed { index, item ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clickable { onOpen(item.route) },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(item.title, style = Body, color = Ink, modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(DRes.drawable.ic_arrow_right),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
                if (index != services.lastIndex) {
                    Spacer(Modifier.fillMaxWidth().height(1.dp).background(LineSoft))
                }
            }

            Spacer(Modifier.height(Space.huge))
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
private fun HelpCircle() {
    Box(Modifier.size(26.dp).background(LineSoft, CircleShape), contentAlignment = Alignment.Center) {
        Text("?", color = Color.White, fontSize = 13.sp)
    }
}

@Preview(device = "spec:width=1080px,height=2400px,dpi=420")
@Composable
private fun ServicesPreview() {
    ReserveTheme { ServicesScreen({}, {}) }
}