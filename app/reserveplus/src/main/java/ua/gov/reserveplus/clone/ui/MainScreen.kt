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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.gov.reserveplus.clone.data.Profile
import ua.gov.reserveplus.design.AccentOrange
import ua.gov.reserveplus.design.BorderSoft
import ua.gov.reserveplus.design.Caption
import ua.gov.reserveplus.design.CardOlive
import ua.gov.reserveplus.design.EUkraine
import ua.gov.reserveplus.design.H1
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.InkMuted
import ua.gov.reserveplus.design.LineSoft
import ua.gov.reserveplus.design.Paper
import ua.gov.reserveplus.design.Radius
import ua.gov.reserveplus.design.ReserveTheme
import ua.gov.reserveplus.design.Sand
import ua.gov.reserveplus.design.Space
import ua.gov.reserveplus.design.SuccessGreen
import ua.gov.reserveplus.design.components.NotificationPill
import ua.gov.reserveplus.design.components.TickerBar
import ua.gov.reserveplus.design.R as DRes

private const val TICKER = "026 • Документ оновлено о 21:31  |  22.06.2026  •  Документ оновлено о 21:31"

private const val DISCHARGE_GROUND = "військовослужбовці"
private const val VLC_VERDICT = "Придатний"
private const val VLC_DATE = "03.10.2025"
private const val POSITION = "Молодших спеціалістів з медичною освітою,Технік зубний"
private const val REGISTRY = "181020201362829700053"
private const val LAST_UPDATE = "03.10.2025"

private fun displayName(raw: String): String {
    val parts = raw.trim().split(" ").filter { it.isNotBlank() }
    if (parts.size < 3) return raw.uppercase()
    val head = parts.dropLast(1).joinToString("\n") { it.uppercase() }
    val tail = parts.last().replaceFirstChar { it.uppercase() }
    return "$head\n$tail"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onOpenQr: () -> Unit) {
    var sheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Column(
        Modifier
            .fillMaxSize()
            .background(Sand)
            .statusBarsPadding()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
                .padding(top = 12.dp, bottom = Space.s),
            horizontalArrangement = Arrangement.End
        ) {
            NotificationPill()
        }

        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 25.dp)
                .padding(bottom = 100.dp)
        ) {
            Spacer(Modifier.height(96.dp))
            PreviewCard(onOpen = { sheet = true }, onQr = onOpenQr)
            Spacer(Modifier.height(Space.xxl))
        }
    }

    if (sheet) {
        ModalBottomSheet(
            onDismissRequest = { sheet = false },
            sheetState = sheetState,
            containerColor = Sand,
            modifier = Modifier.fillMaxWidth()
        ) {
            SheetContent()
        }
    }
}

@Composable
private fun PreviewCard(onOpen: () -> Unit, onQr: () -> Unit) {
    Box(Modifier.fillMaxWidth()) {
        Column(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(CardOlive)
                .border(1.dp, BorderSoft, RoundedCornerShape(20.dp))
                .clickable { onOpen() }
        ) {
            Column(Modifier.padding(horizontal = Space.xl)) {
                Spacer(Modifier.height(Space.xl))
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
                    Text(
                        "Резерв ID",
                        style = H1.copy(
                            fontSize = 30.sp,
                            fontFamily = EUkraine,
                            fontWeight = FontWeight.Normal
                        ),
                        color = Ink,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(DRes.drawable.ic_trident),
                        contentDescription = null,
                        tint = Ink,
                        modifier = Modifier.width(40.dp).height(48.dp)
                    )
                }
                Spacer(Modifier.height(Space.l))
                Row {
                    Image(
                        painter = painterResource(DRes.drawable.photo_owner),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(162.dp)
                            .height(210.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(Modifier.width(Space.l))
                    Column(Modifier.weight(1f)) {
                        Text(
                            "Дата народження:",
                            style = Caption.copy(fontSize = 17.sp),
                            color = InkMuted
                        )
                        Spacer(Modifier.height(Space.xxs))
                        Text(
                            Profile.BIRTH_DATE,
                            style = H1.copy(
                                fontSize = 19.sp,
                                fontFamily = EUkraine,
                                fontWeight = FontWeight.Normal
                            ),
                            color = Ink
                        )
                    }
                }
                Spacer(Modifier.height(64.dp))
            }

            TickerBar(text = TICKER, textSize = 17.sp)

            Column(Modifier.padding(horizontal = Space.xl)) {
                Spacer(Modifier.height(Space.l))
                Text("Знято з обліку", style = Caption.copy(fontSize = 18.sp), color = InkMuted)
                Spacer(Modifier.height(Space.xs))
                Text(
                    displayName(Profile.FULL_NAME),
                    style = H1.copy(
                        fontSize = 32.sp,
                        lineHeight = 35.sp,
                        fontFamily = EUkraine,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Ink,
                    modifier = Modifier.fillMaxWidth().padding(end = 60.dp)
                )
                Spacer(Modifier.height(56.dp))
            }
        }

        Box(
            Modifier
                .align(Alignment.BottomEnd)
                .padding(end = Space.xl, bottom = 36.dp)
                .size(52.dp)
                .background(AccentOrange, CircleShape)
                .clickable { onQr() },
            contentAlignment = Alignment.Center
        ) {
            Canvas(Modifier.size(20.dp)) {
                val c = size.width / 2f
                drawLine(Ink, Offset(c, 0f), Offset(c, size.height), 3.2f, StrokeCap.Butt)
                drawLine(Ink, Offset(0f, c), Offset(size.width, c), 3.2f, StrokeCap.Butt)
            }
        }
    }
}

@Composable
private fun SheetContent() {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.92f)
            .verticalScroll(rememberScrollState())
    ) {
        Row(Modifier.fillMaxWidth().padding(horizontal = Space.xl)) {
            Text(
                "Резерв ID",
                style = H1.copy(
                    fontSize = 30.sp,
                    fontFamily = EUkraine,
                    fontWeight = FontWeight.Normal
                ),
                color = Ink,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(DRes.drawable.ic_trident),
                contentDescription = null,
                tint = Ink,
                modifier = Modifier.width(40.dp).height(48.dp)
            )
        }
        Spacer(Modifier.height(Space.l))
        TickerBar(
            text = TICKER,
            textSize = 17.sp,
            modifier = Modifier
                .padding(horizontal = Space.xl)
                .clip(RoundedCornerShape(Radius.s))
        )
        Spacer(Modifier.height(Space.l))

        Column(Modifier.padding(horizontal = Space.xl)) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Radius.card))
                    .background(Paper)
                    .padding(Space.xl)
            ) {
                Text(
                    displayName(Profile.FULL_NAME),
                    style = H1.copy(
                        fontSize = 32.sp,
                        lineHeight = 35.sp,
                        fontFamily = EUkraine,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Ink
                )
                Spacer(Modifier.height(Space.l))
                Text("Знято з обліку", style = Caption.copy(fontSize = 18.sp), color = Ink)
                Spacer(Modifier.height(Space.l))
                Row {
                    Image(
                        painter = painterResource(DRes.drawable.photo_owner),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(162.dp)
                            .height(210.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(Modifier.width(Space.l))
                    Column(Modifier.weight(1f)) {
                        Text("Дата народження:", style = Caption.copy(fontSize = 15.sp), color = InkMuted)
                        Spacer(Modifier.height(Space.xxs))
                        Text(Profile.BIRTH_DATE, style = Caption.copy(fontSize = 18.sp), color = Ink)
                        Spacer(Modifier.height(Space.l))
                        Text("РНОКПП:", style = Caption.copy(fontSize = 15.sp), color = InkMuted)
                        Spacer(Modifier.height(Space.xxs))
                        Text(Profile.TAX_ID, style = Caption.copy(fontSize = 18.sp), color = Ink)
                    }
                }
            }

            Spacer(Modifier.height(Space.m))
            FieldSections()
            Spacer(Modifier.height(Space.huge))
        }
    }
}

@Composable
private fun FieldSections() {
    Column(Modifier.fillMaxWidth()) {
        CardBlock {
            Text("Підстава зняття чи виключення:", style = Caption.copy(fontSize = 14.sp), color = Ink)
            Spacer(Modifier.height(Space.xs))
            Text(DISCHARGE_GROUND, style = Caption.copy(fontSize = 14.sp), color = Ink)
        }
        CardBlock {
            Text("Постанова ВЛК:", style = Caption.copy(fontSize = 14.sp), color = Ink)
            Spacer(Modifier.height(Space.xs))
            Text(VLC_VERDICT, style = Caption.copy(fontSize = 14.sp), color = Ink)
            Spacer(Modifier.height(Space.m))
            Divider()
            Spacer(Modifier.height(Space.m))
            Row(Modifier.fillMaxWidth()) {
                Text("Дата ВЛК:", style = Caption.copy(fontSize = 14.sp), color = Ink, modifier = Modifier.weight(1f))
                Text(VLC_DATE, style = Caption.copy(fontSize = 14.sp), color = Ink)
            }
        }
        CardBlock {
            Text("ТЦК та СП:", style = Caption.copy(fontSize = 14.sp), color = Ink)
            Spacer(Modifier.height(Space.xs))
            Text(Profile.MILITARY_OFFICE, style = Caption.copy(fontSize = 14.sp), color = Ink)
            Spacer(Modifier.height(Space.m))
            Divider()
            Spacer(Modifier.height(Space.m))
            Row(Modifier.fillMaxWidth()) {
                Text("Звання", style = Caption.copy(fontSize = 14.sp), color = Ink, modifier = Modifier.weight(1f))
                Text(Profile.RANK, style = Caption.copy(fontSize = 14.sp), color = Ink)
            }
            Spacer(Modifier.height(Space.m))
            Row(Modifier.fillMaxWidth()) {
                Text("ВОС:", style = Caption.copy(fontSize = 14.sp), color = Ink, modifier = Modifier.weight(1f))
                Text(Profile.VOS, style = Caption.copy(fontSize = 14.sp), color = Ink)
            }
            Spacer(Modifier.height(Space.m))
            Text(POSITION, style = Caption.copy(fontSize = 14.sp), color = Ink)
            Spacer(Modifier.height(Space.m))
            Text("Категорія обліку:", style = Caption.copy(fontSize = 14.sp), color = Ink)
            Spacer(Modifier.height(Space.xs))
            Text(Profile.CATEGORY, style = Caption.copy(fontSize = 14.sp), color = Ink)
            Spacer(Modifier.height(Space.m))
            Text("Номер в реєстрі Оберіг:", style = Caption.copy(fontSize = 14.sp), color = Ink)
            Spacer(Modifier.height(Space.xs))
            Text(REGISTRY, style = Caption.copy(fontSize = 14.sp), color = Ink)
        }
        CardBlock {
            Text("Телефон:", style = Caption.copy(fontSize = 14.sp), color = Ink)
            Spacer(Modifier.height(Space.xs))
            Text(Profile.PHONE, style = Caption.copy(fontSize = 14.sp), color = Ink)
            Spacer(Modifier.height(Space.m))
            Text("Email:", style = Caption.copy(fontSize = 14.sp), color = Ink)
            Spacer(Modifier.height(Space.xs))
            Text(Profile.EMAIL, style = Caption.copy(fontSize = 14.sp), color = Ink)
            Spacer(Modifier.height(Space.m))
            Text("Адреса проживання:", style = Caption.copy(fontSize = 14.sp), color = Ink)
            Spacer(Modifier.height(Space.xs))
            Text(Profile.ADDRESS, style = Caption.copy(fontSize = 14.sp), color = Ink)
        }
        CardBlock {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Canvas(Modifier.size(20.dp)) {
                    drawCircle(SuccessGreen)
                    val w = size.width
                    val h = size.height
                    val p = Path().apply {
                        moveTo(w * 0.28f, h * 0.52f)
                        lineTo(w * 0.44f, h * 0.68f)
                        lineTo(w * 0.72f, h * 0.34f)
                    }
                    drawPath(
                        p,
                        androidx.compose.ui.graphics.Color.White,
                        style = Stroke(width = w * 0.14f, cap = StrokeCap.Round, join = StrokeJoin.Round)
                    )
                }
                Spacer(Modifier.width(Space.s))
                Text("Дані уточнено вчасно", style = Caption.copy(fontSize = 14.sp), color = Ink)
            }
            Spacer(Modifier.height(Space.l))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    "Дата останнього уточнення даних:",
                    style = Caption.copy(fontSize = 14.sp),
                    color = Ink,
                    modifier = Modifier.weight(1f)
                )
                Text(LAST_UPDATE, style = Caption.copy(fontSize = 14.sp), color = Ink)
            }
        }
    }
}

@Composable
private fun CardBlock(content: @Composable () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(bottom = Space.s)
            .clip(RoundedCornerShape(Radius.card))
            .background(Paper)
            .padding(Space.xl)
    ) {
        content()
    }
}

@Composable
private fun Divider() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(LineSoft)
    )
}

@Preview(device = "spec:width=1080px,height=2400px,dpi=420")
@Composable
private fun MainPreview() {
    ReserveTheme { MainScreen({}) }
}