package ua.gov.reserveplus.design.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.InkMuted
import ua.gov.reserveplus.design.Label
import ua.gov.reserveplus.design.Paper
import ua.gov.reserveplus.design.Space

private data class NavTab(val id: String, val label: String)

private val TABS = listOf(
    NavTab("id", "Резерв ID"),
    NavTab("services", "Сервіси"),
    NavTab("vacancies", "Вакансії"),
    NavTab("menu", "Меню")
)

@Composable
fun BottomNav(selected: String, onSelect: (String) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Paper)
            .navigationBarsPadding()
            .padding(top = Space.l, bottom = Space.m),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        TABS.forEach { tab ->
            val active = tab.id == selected
            Column(
                Modifier
                    .clickable { onSelect(tab.id) }
                    .padding(horizontal = Space.m),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NavIcon(tab.id, if (active) Ink else InkMuted)
                Spacer(Modifier.height(Space.xs))
                Text(
                    tab.label,
                    style = Label.copy(
                        fontSize = 18.sp,
                        fontWeight = if (active) FontWeight.Bold else FontWeight.Normal
                    ),
                    color = if (active) Ink else InkMuted,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
private fun NavIcon(id: String, color: Color) {
    Canvas(Modifier.size(30.dp)) {
        val w = size.width
        val h = size.height
        when (id) {
            "id" -> {
                drawRoundRect(
                    color,
                    topLeft = Offset(w * 0.2f, h * 0.08f),
                    size = Size(w * 0.6f, h * 0.84f),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.12f)
                )
                drawLine(Color.White, Offset(w * 0.33f, h * 0.36f), Offset(w * 0.67f, h * 0.36f), w * 0.08f, StrokeCap.Round)
                drawLine(Color.White, Offset(w * 0.33f, h * 0.56f), Offset(w * 0.58f, h * 0.56f), w * 0.08f, StrokeCap.Round)
            }
            "services" -> {
                val s = w * 0.32f
                listOf(0.12f to 0.12f, 0.56f to 0.12f, 0.12f to 0.56f, 0.56f to 0.56f).forEach { (x, y) ->
                    drawRoundRect(
                        color,
                        topLeft = Offset(w * x, h * y),
                        size = Size(s, s),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.07f),
                        style = Stroke(width = w * 0.09f)
                    )
                }
            }
            "vacancies" -> {
                drawRoundRect(
                    color,
                    topLeft = Offset(w * 0.14f, h * 0.14f),
                    size = Size(w * 0.72f, h * 0.72f),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.1f),
                    style = Stroke(width = w * 0.09f)
                )
                val p = Path().apply {
                    moveTo(w * 0.5f, h * 0.32f)
                    lineTo(w * 0.68f, h * 0.5f)
                    lineTo(w * 0.5f, h * 0.68f)
                    lineTo(w * 0.32f, h * 0.5f)
                    close()
                }
                drawPath(p, color)
            }
            else -> {
                drawLine(color, Offset(w * 0.14f, h * 0.3f), Offset(w * 0.86f, h * 0.3f), w * 0.11f, StrokeCap.Round)
                drawLine(color, Offset(w * 0.14f, h * 0.5f), Offset(w * 0.86f, h * 0.5f), w * 0.11f, StrokeCap.Round)
                drawLine(color, Offset(w * 0.14f, h * 0.7f), Offset(w * 0.86f, h * 0.7f), w * 0.11f, StrokeCap.Round)
            }
        }
    }
}