package ua.gov.reserveplus.clone.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.gov.reserveplus.design.AccentOrange
import ua.gov.reserveplus.design.Caption
import ua.gov.reserveplus.design.CardSurface
import ua.gov.reserveplus.design.EUkraine
import ua.gov.reserveplus.design.H1
import ua.gov.reserveplus.design.H3
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.InkMuted
import ua.gov.reserveplus.design.Label
import ua.gov.reserveplus.design.LineSoft
import ua.gov.reserveplus.design.Radius
import ua.gov.reserveplus.design.ReserveTheme
import ua.gov.reserveplus.design.Sand
import ua.gov.reserveplus.design.SandSunken
import ua.gov.reserveplus.design.Space
import ua.gov.reserveplus.design.StripOlive
import ua.gov.reserveplus.design.SuccessGreen
import ua.gov.reserveplus.design.components.BrandWordmark
import ua.gov.reserveplus.design.components.NotificationPill
import ua.gov.reserveplus.design.R as DRes
import ua.gov.reserveplus.design.components.TickerBar

@Composable
fun MainScreen(
    onOpenDocument: () -> Unit,
    onOpenServices: () -> Unit,
    onOpenService: (String) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Sand)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = Space.screen),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BrandWordmark()
            NotificationPill(count = "2")
        }

        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Space.screen)
                .navigationBarsPadding()
        ) {
            Spacer(Modifier.height(Space.l))
            MilitaryDocumentCard(onClick = onOpenDocument)
            Spacer(Modifier.height(Space.l))
            IdentityCard()
            Spacer(Modifier.height(Space.xxl))
            ServicesSection(onOpenService)
            Spacer(Modifier.height(Space.l))
            AllServicesCard(onOpenServices)
            Spacer(Modifier.height(Space.l))
            QueueBanner()
            Spacer(Modifier.height(Space.huge))
        }
    }
}

@Composable
private fun CardTrident() {
    Icon(
        painter = painterResource(DRes.drawable.ic_trident),
        contentDescription = null,
        tint = Ink,
        modifier = Modifier.width(24.dp).height(28.dp)
    )
}

@Composable
private fun MilitaryDocumentCard(onClick: () -> Unit) {
    Box(Modifier.fillMaxWidth().clickable { onClick() }) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(CardSurface, RoundedCornerShape(Radius.card))
                .padding(Space.xl)
        ) {
            Text(
                "Військово-\nобліковий документ",
                style = H1.copy(fontSize = 26.sp, lineHeight = 29.sp),
                color = Ink,
                modifier = Modifier.fillMaxWidth().padding(end = 34.dp)
            )
            Spacer(Modifier.height(Space.xxl))
            Row(verticalAlignment = Alignment.CenterVertically) {
                CheckBadge()
                Spacer(Modifier.width(Space.s))
                Text("Дані уточнено вчасно", style = Caption, color = SuccessGreen)
            }
        }
        Box(Modifier.align(Alignment.TopEnd).padding(top = Space.xl, end = Space.xl)) {
            CardTrident()
        }
    }
}

@Composable
private fun IdentityCard() {
    Box(Modifier.fillMaxWidth()) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(CardSurface, RoundedCornerShape(Radius.card))
                .padding(Space.xl)
        ) {
            Text(
                "Резерв ID",
                style = H1.copy(fontSize = 24.sp),
                color = Ink,
                modifier = Modifier.fillMaxWidth().padding(end = 34.dp)
            )
            Spacer(Modifier.height(Space.l))

            Row {
                Image(
                    painter = painterResource(DRes.drawable.photo_owner),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(84.dp)
                        .height(104.dp)
                        .clip(RoundedCornerShape(Radius.s))
                        .border(1.dp, LineSoft, RoundedCornerShape(Radius.s))
                )
                Spacer(Modifier.width(Space.xl))
                Column(Modifier.weight(1f)) {
                    Text("Дата народження", style = Label, color = InkMuted)
                    Text("24.08.1991", style = H1.copy(fontSize = 20.sp), color = Ink)
                    Spacer(Modifier.height(Space.l))
                    Text("Відстрочка до:", style = Label, color = InkMuted)
                    Text("24.08.2026", style = H1.copy(fontSize = 20.sp), color = Ink)
                }
            }

            Spacer(Modifier.height(Space.xl))

            TickerBar(
                text = "Документ оновлено о 18:42 · Натисніть, щоб переглянути",
                modifier = Modifier.clip(RoundedCornerShape(Radius.s))
            )

            Spacer(Modifier.height(Space.xl))

            Text("Резервіст", style = Caption, color = InkMuted)
            Spacer(Modifier.height(Space.xs))
            Text(
                "Воля Святослав Володимирович",
                style = H1.copy(fontSize = 24.sp, lineHeight = 27.sp, fontFamily = EUkraine, fontWeight = FontWeight.Bold),
                color = Ink
            )
            Spacer(Modifier.height(40.dp))
        }

        Box(Modifier.align(Alignment.TopEnd).padding(top = Space.xl, end = Space.xl)) {
            CardTrident()
        }

        Box(
            Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-20).dp, y = 24.dp)
                .size(52.dp)
                .background(AccentOrange, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Canvas(Modifier.size(14.dp)) {
                val c = size.width / 2f
                drawLine(Ink, Offset(c, 0f), Offset(c, size.height), 2.4f, StrokeCap.Round)
                drawLine(Ink, Offset(0f, c), Offset(size.width, c), 2.4f, StrokeCap.Round)
            }
        }
    }
}

@Composable
private fun ServicesSection(onOpen: (String) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(CardSurface, RoundedCornerShape(Radius.card))
            .padding(Space.xl)
    ) {
        Text("Сервіси", style = H1.copy(fontSize = 22.sp), color = Ink)
        Spacer(Modifier.height(Space.xl))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(Space.m)) {
            ServiceTile("Відстрочка", { onOpen("deferment") }, Modifier.weight(1f)) {
                Icon(
                    painter = painterResource(DRes.drawable.ic_full_info),
                    contentDescription = null,
                    tint = Ink,
                    modifier = Modifier.size(22.dp)
                )
            }
            ServiceTile("Виправити", { onOpen("services") }, Modifier.weight(1f)) {
                Icon(
                    painter = painterResource(DRes.drawable.ic_refresh),
                    contentDescription = null,
                    tint = Ink,
                    modifier = Modifier.size(22.dp)
                )
            }
            ServiceTile("На ВЛК", { onOpen("services") }, Modifier.weight(1f)) {
                Icon(
                    painter = painterResource(DRes.drawable.ic_my_document),
                    contentDescription = null,
                    tint = Ink,
                    modifier = Modifier.size(22.dp)
                )
            }
            ServiceTile("Штрафи", { onOpen("fines") }, Modifier.weight(1f)) {
                AlertIcon()
            }
        }
    }
}

@Composable
private fun ServiceTile(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit
) {
    Column(
        modifier = modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .size(48.dp)
                .background(SandSunken, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }
        Spacer(Modifier.height(Space.m))
        Text(
            title,
            style = Label.copy(fontSize = 10.sp),
            color = Ink,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Composable
private fun AlertIcon() {
    Canvas(Modifier.size(22.dp)) {
        val w = size.width
        drawCircle(
            Ink,
            radius = w / 2f - w * 0.045f,
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = w * 0.09f)
        )
        drawLine(
            Ink,
            Offset(w * 0.5f, w * 0.27f),
            Offset(w * 0.5f, w * 0.55f),
            w * 0.09f,
            StrokeCap.Round
        )
        drawCircle(Ink, radius = w * 0.055f, center = Offset(w * 0.5f, w * 0.71f))
    }
}

@Composable
private fun AllServicesCard(onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(CardSurface, RoundedCornerShape(Radius.card))
            .clickable { onClick() }
            .padding(Space.xl),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(DRes.drawable.ic_services),
            contentDescription = null,
            tint = Ink,
            modifier = Modifier.size(22.dp)
        )
        Spacer(Modifier.width(Space.l))
        Text("Усі сервіси", style = H3, color = Ink, modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(DRes.drawable.ic_arrow_right),
            contentDescription = null,
            tint = Ink,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun QueueBanner() {
    Row(
        Modifier
            .fillMaxWidth()
            .background(StripOlive, RoundedCornerShape(Radius.card))
            .padding(horizontal = Space.xl, vertical = Space.xl),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text("Електронна черга до ТЦК", style = H3, color = Sand)
            Space.xs
            Text("Записатися онлайн", style = Caption, color = Sand.copy(alpha = 0.75f))
        }
        Icon(
            painter = painterResource(DRes.drawable.ic_arrow_right),
            contentDescription = null,
            tint = Sand,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun CheckBadge(size: Int = 20) {
    Canvas(Modifier.size(size.dp)) {
        drawCircle(SuccessGreen)
        val w = this.size.width
        val h = this.size.height
        val p = androidx.compose.ui.graphics.Path().apply {
            moveTo(w * 0.28f, h * 0.52f)
            lineTo(w * 0.44f, h * 0.68f)
            lineTo(w * 0.72f, h * 0.34f)
        }
        drawPath(
            p,
            androidx.compose.ui.graphics.Color.White,
            style = androidx.compose.ui.graphics.drawscope.Stroke(
                width = w * 0.14f,
                cap = StrokeCap.Round,
                join = androidx.compose.ui.graphics.StrokeJoin.Round
            )
        )
    }
}

@Preview(device = "spec:width=1080px,height=2400px,dpi=420")
@Composable
private fun MainPreview() {
    ReserveTheme { MainScreen({}, {}, {}) }
}