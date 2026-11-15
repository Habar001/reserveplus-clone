package ua.gov.reserveplus.design

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val EUkraine = FontFamily(
    Font(R.font.e_ukraine_light, FontWeight.Light),
    Font(R.font.e_ukraine_regular, FontWeight.Normal),
    Font(R.font.e_ukraine_medium, FontWeight.Medium),
    Font(R.font.e_ukraine_bold, FontWeight.Bold)
)

val Display = TextStyle(fontFamily = EUkraine, fontWeight = FontWeight.Bold, fontSize = 34.sp, lineHeight = 38.sp, letterSpacing = (-0.5).sp)
val H1 = TextStyle(fontFamily = EUkraine, fontWeight = FontWeight.Bold, fontSize = 26.sp, lineHeight = 30.sp, letterSpacing = (-0.3).sp)
val H2 = TextStyle(fontFamily = EUkraine, fontWeight = FontWeight.Bold, fontSize = 20.sp, lineHeight = 24.sp)
val H3 = TextStyle(fontFamily = EUkraine, fontWeight = FontWeight.Medium, fontSize = 17.sp, lineHeight = 22.sp)
val Body = TextStyle(fontFamily = EUkraine, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 22.sp)
val BodySmall = TextStyle(fontFamily = EUkraine, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 19.sp)
val Caption = TextStyle(fontFamily = EUkraine, fontWeight = FontWeight.Medium, fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.2.sp)
val Label = TextStyle(fontFamily = EUkraine, fontWeight = FontWeight.Medium, fontSize = 11.sp, lineHeight = 14.sp, letterSpacing = 0.6.sp)
val ButtonText = TextStyle(fontFamily = EUkraine, fontWeight = FontWeight.Medium, fontSize = 16.sp, lineHeight = 20.sp)

val ReserveTypography = Typography(
    displayLarge = Display,
    headlineLarge = H1,
    headlineMedium = H2,
    titleMedium = H3,
    bodyLarge = Body,
    bodyMedium = BodySmall,
    labelLarge = ButtonText,
    labelSmall = Caption
)