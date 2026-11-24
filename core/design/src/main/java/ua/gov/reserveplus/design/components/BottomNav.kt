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
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import ua.gov.reserveplus.design.R as DRes

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
            .padding(top = 10.dp, bottom = 6.dp),
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
                NavIcon(tab.id, active)
                Spacer(Modifier.height(Space.xs))
                Text(
                    tab.label,
                    style = Label.copy(fontSize = 10.sp),
                    color = Ink
                )
            }
        }
    }
}

@Composable
private fun NavIcon(id: String, active: Boolean) {
    val res = when (id) {
        "id" -> if (active) DRes.drawable.ic_nav_document_sel else DRes.drawable.ic_nav_document
        "services" -> if (active) DRes.drawable.ic_nav_services_sel else DRes.drawable.ic_nav_services
        "vacancies" -> if (active) DRes.drawable.ic_nav_vacancies_sel else DRes.drawable.ic_nav_vacancies
        else -> if (active) DRes.drawable.ic_nav_menu_sel else DRes.drawable.ic_nav_menu
    }
    Icon(
        painter = painterResource(res),
        contentDescription = null,
        tint = if (active) Color.Unspecified else Ink,
        modifier = Modifier.size(24.dp)
    )
}