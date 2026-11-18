package ua.gov.reserveplus.clone.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import ua.gov.reserveplus.design.Caption
import ua.gov.reserveplus.design.CardSurface
import ua.gov.reserveplus.design.H1
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.InkMuted
import ua.gov.reserveplus.design.Label
import ua.gov.reserveplus.design.Radius
import ua.gov.reserveplus.design.Sand
import ua.gov.reserveplus.design.Space

@Composable
fun VacanciesScreen() {
    TabScaffold("Вакансії") {
        SimpleCard("Стрілець", "3 окрема штурмова бригада · Київ")
        SimpleCard("Водій", "47 окрема механізована бригада · Полтава")
        SimpleCard("Оператор БпЛА", "93 окрема механізована бригада · Харків")
    }
}

@Composable
fun MenuScreen() {
    TabScaffold("Меню") {
        SimpleCard("Профіль", "Особисті дані")
        SimpleCard("Сповіщення", "2 нових")
        SimpleCard("Підтримка", "Зв'язок з адміністратором")
        SimpleCard("Про застосунок", "Версія 1.0")
    }
}

@Composable
private fun TabScaffold(title: String, content: @Composable () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Sand)
            .statusBarsPadding()
            .padding(horizontal = Space.screen)
    ) {
        Text(
            title,
            style = H1.copy(fontSize = 24.sp),
            color = Ink,
            modifier = Modifier.padding(vertical = Space.l)
        )
        Column(Modifier.verticalScroll(rememberScrollState())) {
            content()
            Spacer(Modifier.height(Space.xxl))
        }
    }
}

@Composable
private fun SimpleCard(title: String, subtitle: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(bottom = Space.s)
            .background(CardSurface, RoundedCornerShape(Radius.card))
            .padding(Space.xl)
    ) {
        Text(title, style = Label.copy(fontSize = 15.sp), color = Ink)
        Spacer(Modifier.height(Space.xs))
        Text(subtitle, style = Caption, color = InkMuted)
    }
}