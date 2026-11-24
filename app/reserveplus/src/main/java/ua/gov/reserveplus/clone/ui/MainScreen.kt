package ua.gov.reserveplus.clone.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import ua.gov.reserveplus.design.SandSunken
import ua.gov.reserveplus.design.Space
import ua.gov.reserveplus.design.SuccessGreen
import ua.gov.reserveplus.design.components.NotificationPill
import ua.gov.reserveplus.design.components.TickerBar
import ua.gov.reserveplus.design.R as DRes
import androidx.compose.ui.unit.Dp
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle

private const val TICKER = "026 • Документ оновлено о 21:31  |  22.06.2026  •  Документ оновлено о 21:31   •   "

private const val DISCHARGE_GROUND = "військовослужбовці"
private const val VLC_VERDICT = "Придатний"
private const val VLC_DATE = "03.10.2025"
private const val POSITION = "Молодших спеціалістів з медичною освітою,Технік зубний"
private const val REGISTRY = "181020201362829700053"
private const val LAST_UPDATE = "03.10.2025"
private const val QR_HINT = "Використовуйте QR-код лише за потреби та відповідально ставтеся до його поширення."

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
    var panel by remember { mutableStateOf<String?>(null) }
    var flipped by remember { mutableStateOf(false) }
    val panelState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

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
            Spacer(Modifier.height(100.dp))
            FlipCard(
                flipped = flipped,
                front = {
                    PreviewCard(
                        onOpen = { flipped = true },
                        onPlus = { panel = "menu" }
                    )
                },
                back = {
                    QrCard(onBack = { flipped = false })
                }
            )
            Spacer(Modifier.height(Space.xxl))
        }
    }

    val menuOpen = panel == "menu"

    if (panel != null) {
        ModalBottomSheet(
            onDismissRequest = { panel = null },
            sheetState = panelState,
            containerColor = if (menuOpen) Paper else Sand,
            sheetMaxWidth = Dp.Unspecified,
            shape = if (menuOpen) RoundedCornerShape(28.dp) else RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            dragHandle = {
                Box(
                    Modifier
                        .padding(top = 10.dp, bottom = 8.dp)
                        .size(width = if (menuOpen) 42.dp else 44.dp, height = 6.dp)
                        .background(Ink, RoundedCornerShape(3.dp))
                )
            },
            modifier = if (menuOpen) {
                Modifier
                    .fillMaxWidth()
                    .padding(start = 19.dp, end = 19.dp, bottom = 40.dp)
            } else {
                Modifier.fillMaxWidth()
            }
        ) {
            if (menuOpen) {
                MenuSheet(onDocument = { panel = "doc" })
            } else {
                SheetContent()
            }
        }
    }
}

@Composable
private fun FlipCard(
    flipped: Boolean,
    front: @Composable () -> Unit,
    back: @Composable () -> Unit
) {
    val rotation by animateFloatAsState(
        targetValue = if (flipped) 180f else 0f,
        animationSpec = tween(durationMillis = 520),
        label = "flip"
    )

    Box(
        Modifier
            .fillMaxWidth()
            .height(537.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            }
    ) {
        if (rotation <= 90f) {
            front()
        } else {
            Box(Modifier.graphicsLayer { rotationY = 180f }) {
                back()
            }
        }
    }
}

@Composable
private fun PreviewCard(onOpen: () -> Unit, onPlus: () -> Unit) {
    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .background(CardOlive)
                .border(1.dp, BorderSoft, RoundedCornerShape(20.dp))
                .clickable { onOpen() }
        ) {
            Column(Modifier.padding(horizontal = Space.xl)) {
                Spacer(Modifier.height(20.dp))
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
                    Text(
                        "Резерв ID",
                        style = H1.copy(
                            fontSize = 23.sp,
                            lineHeight = 28.sp,
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
                        modifier = Modifier.width(32.dp).height(40.dp)
                    )
                }
                Spacer(Modifier.height(16.dp))
                Row {
                    Image(
                        painter = painterResource(DRes.drawable.photo_owner),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(164.dp)
                            .height(222.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(Modifier.width(22.dp))
                    Column(Modifier.weight(1f)) {
                        Text(
                            "Дата народження:",
                            style = Caption.copy(fontSize = 14.sp, lineHeight = 19.sp),
                            color = InkMuted,
                            maxLines = 1,
                            softWrap = false
                        )
                        Spacer(Modifier.height(Space.xxs))
                        Text(
                            Profile.BIRTH_DATE,
                            style = H1.copy(
                                fontSize = 18.sp,
                                lineHeight = 23.sp,
                                fontFamily = EUkraine,
                                fontWeight = FontWeight.Normal
                            ),
                            color = Ink
                        )
                    }
                }
                Spacer(Modifier.height(64.dp))
            }

            TickerBar(text = TICKER, textSize = 12.sp)

            Column(Modifier.padding(horizontal = Space.xl)) {
                Spacer(Modifier.height(43.dp))
                Text(
                    "Знято з обліку",
                    style = Caption.copy(fontSize = 13.sp, lineHeight = 17.sp),
                    color = InkMuted
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    displayName(Profile.FULL_NAME),
                    style = H1.copy(
                        fontSize = 24.sp,
                        lineHeight = 21.sp,
                        fontFamily = EUkraine,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Ink,
                    modifier = Modifier.fillMaxWidth().padding(end = 64.dp)
                )
                Spacer(Modifier.height(2.dp))
            }
        }

        Box(
            Modifier
                .align(Alignment.BottomEnd)
                .padding(end = Space.xl, bottom = 14.dp)
                .size(46.dp)
                .background(AccentOrange, CircleShape)
                .clickable { onPlus() },
            contentAlignment = Alignment.Center
        ) {
            Canvas(Modifier.size(20.dp)) {
                val c = size.width / 2f
                drawLine(Ink, Offset(c, 0f), Offset(c, size.height), 4.5f, StrokeCap.Butt)
                drawLine(Ink, Offset(0f, c), Offset(size.width, c), 4.5f, StrokeCap.Butt)
            }
        }
    }
}

@Composable
private fun QrCard(onBack: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))
            .background(Paper)
            .border(1.dp, BorderSoft, RoundedCornerShape(20.dp))
            .clickable { onBack() }
            .padding(horizontal = 26.dp)
            .padding(top = 40.dp, bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "QR-код дійсний до " + Profile.QR_VALID_UNTIL,
            style = Caption.copy(
                fontSize = 13.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight.Normal
            ),
            color = Ink,
            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(14.dp))
        Image(
            painter = painterResource(DRes.drawable.qr_default),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(268.dp)
        )
        Spacer(Modifier.height(30.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(CardOlive)
                .border(1.dp, BorderSoft, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Box(
                Modifier
                    .size(19.dp)
                    .border(1.4.dp, InkMuted, RoundedCornerShape(5.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "i",
                    style = Caption.copy(
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Italic
                    ),
                    color = Ink
                )
            }
            Spacer(Modifier.width(16.dp))
            Text(
                "Використовуйте QR-код лише за потреби та відповідально ставтеся до його поширення.",
                style = Caption.copy(
                    fontSize = 14.sp,
                    lineHeight = 19.sp,
                    fontWeight = FontWeight.Normal
                ),
                color = Ink
            )
        }
    }
}

@Composable
private fun QrImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(DRes.drawable.qr_default),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
    )
}

@Composable
private fun MenuSheet(onDocument: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 23.dp)
            .padding(top = 30.dp, bottom = 8.dp)
    ) {
        MenuRow(DRes.drawable.ic_menu_info, "Переглянути документ", onClick = onDocument)
        Spacer(Modifier.height(28.dp))
        MenuRow(DRes.drawable.ic_menu_pdf, "Завантажити PDF", onClick = {})
        Spacer(Modifier.height(28.dp))
        MenuRow(DRes.drawable.ic_menu_refresh, "Оновити документ", onClick = {})
    }
}

@Composable
private fun MenuRow(icon: Int, text: String, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = Space.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Ink,
            modifier = Modifier.size(27.dp)
        )
        Spacer(Modifier.width(Space.xl))
        Text(
            text,
            style = H1.copy(
                fontSize = 20.sp,
                lineHeight = 26.sp,
                fontFamily = EUkraine,
                fontWeight = FontWeight.Normal
            ),
            color = Ink,
            maxLines = 1,
            softWrap = false
        )
    }
}

@Composable
private fun SheetContent() {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.96f)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(50.dp))
        Row(Modifier.fillMaxWidth().padding(horizontal = 26.dp)) {
            Text(
                "Резерв ID",
                style = H1.copy(
                    fontSize = 32.sp,
                    lineHeight = 36.sp,
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
                modifier = Modifier.width(36.dp).height(42.dp)
            )
        }
        Spacer(Modifier.height(Space.l))
        TickerBar(
            text = TICKER,
            textSize = 13.sp
        )
        Spacer(Modifier.height(28.dp))

        Column(Modifier.padding(horizontal = 26.dp)) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Radius.card))
                    .background(Paper)
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 19.dp)
            ) {
                Text(
                    displayName(Profile.FULL_NAME),
                    style = H1.copy(
                        fontSize = 21.sp,
                        lineHeight = 24.sp,
                        fontFamily = EUkraine,
                        fontWeight = FontWeight.Normal
                    ),
                    color = Ink
                )
                Spacer(Modifier.height(Space.l))
                Text("Знято з обліку", style = Caption.copy(fontSize = 15.sp, lineHeight = 20.sp, fontWeight = FontWeight.Normal), color = Ink)
                Spacer(Modifier.height(Space.l))
                Row {
                    Image(
                        painter = painterResource(DRes.drawable.photo_owner),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(142.dp)
                            .height(178.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(Modifier.width(Space.l))
                    Column(Modifier.weight(1f)) {
                        Text("Дата народження:", style = Caption.copy(fontSize = 15.sp, lineHeight = 20.sp, fontWeight = FontWeight.Normal), color = Ink, maxLines = 1, softWrap = false)
                        Spacer(Modifier.height(6.dp))
                        Text(Profile.BIRTH_DATE, style = Caption.copy(fontSize = 15.sp, lineHeight = 20.sp, fontWeight = FontWeight.Normal), color = Ink)
                        Spacer(Modifier.height(Space.l))
                        Text("РНОКПП:", style = Caption.copy(fontSize = 15.sp, lineHeight = 20.sp, fontWeight = FontWeight.Normal), color = Ink)
                        Spacer(Modifier.height(6.dp))
                        Text(Profile.TAX_ID, style = Caption.copy(fontSize = 15.sp, lineHeight = 20.sp, fontWeight = FontWeight.Normal), color = Ink)
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
            Text("Підстава зняття чи виключення:", style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            Spacer(Modifier.height(Space.xs))
            Text(DISCHARGE_GROUND, style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
        }
        CardBlock {
            Text("Постанова ВЛК:", style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            Spacer(Modifier.height(Space.xs))
            Text(VLC_VERDICT, style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            Spacer(Modifier.height(14.dp))
            Divider()
            Spacer(Modifier.height(14.dp))
            Row(Modifier.fillMaxWidth()) {
                Text("Дата ВЛК:", style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink, modifier = Modifier.weight(1f))
                Text(VLC_DATE, style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            }
        }
        CardBlock {
            Text("ТЦК та СП:", style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            Spacer(Modifier.height(Space.xs))
            Text(Profile.MILITARY_OFFICE, style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            Spacer(Modifier.height(14.dp))
            Divider()
            Spacer(Modifier.height(14.dp))
            Row(Modifier.fillMaxWidth()) {
                Text("Звання", style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink, modifier = Modifier.weight(1f))
                Text(Profile.RANK, style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            }
            Spacer(Modifier.height(14.dp))
            Row(Modifier.fillMaxWidth()) {
                Text("ВОС:", style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink, modifier = Modifier.weight(1f))
                Text(Profile.VOS, style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            }
            Spacer(Modifier.height(14.dp))
            Text(POSITION, style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            Spacer(Modifier.height(14.dp))
            Text("Категорія обліку:", style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            Spacer(Modifier.height(Space.xs))
            Text(Profile.CATEGORY, style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            Spacer(Modifier.height(14.dp))
            Text("Номер в реєстрі Оберіг:", style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            Spacer(Modifier.height(Space.xs))
            Text(REGISTRY, style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
        }
        CardBlock {
            Text("Телефон:", style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            Spacer(Modifier.height(Space.xs))
            Text(Profile.PHONE, style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            Spacer(Modifier.height(14.dp))
            Text("Email:", style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            Spacer(Modifier.height(Space.xs))
            Text(Profile.EMAIL, style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            Spacer(Modifier.height(14.dp))
            Text("Адреса проживання:", style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            Spacer(Modifier.height(Space.xs))
            Text(Profile.ADDRESS, style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
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
                Text("Дані уточнено вчасно", style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            }
            Spacer(Modifier.height(Space.l))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    "Дата останнього уточнення даних:",
                    style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal),
                    color = Ink,
                    modifier = Modifier.weight(1f)
                )
                Text(LAST_UPDATE, style = Caption.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal), color = Ink)
            }
        }
    }
}

@Composable
private fun CardBlock(content: @Composable () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 19.dp)
            .clip(RoundedCornerShape(Radius.card))
            .background(Paper)
            .padding(15.dp)
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