package ua.gov.reserveplus.design.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.gov.reserveplus.design.AccentOrange
import ua.gov.reserveplus.design.EUkraine
import ua.gov.reserveplus.design.Ink

@Composable
fun BrandWordmark(modifier: Modifier = Modifier, size: Int = 20) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Резерв",
            color = Ink,
            fontFamily = EUkraine,
            fontWeight = FontWeight.Bold,
            fontSize = size.sp
        )
        Text(
            text = "+",
            color = AccentOrange,
            fontFamily = EUkraine,
            fontWeight = FontWeight.Bold,
            fontSize = (size * 0.7f).sp,
            modifier = Modifier.offset(y = (-size * 0.45f).dp)
        )
    }
}