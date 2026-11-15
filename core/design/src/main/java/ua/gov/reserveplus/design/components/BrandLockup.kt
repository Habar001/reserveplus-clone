package ua.gov.reserveplus.design.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.gov.reserveplus.design.AccentOrange
import ua.gov.reserveplus.design.EUkraine
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.R

@Composable
fun BrandLockup(modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(R.drawable.ic_trident),
            contentDescription = null,
            tint = Ink,
            modifier = Modifier.width(19.dp).height(22.dp)
        )
        Spacer(Modifier.width(6.dp))
        Text(
            text = "Резерв",
            color = Ink,
            fontFamily = EUkraine,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = "+",
            color = AccentOrange,
            fontFamily = EUkraine,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.offset(y = (-9).dp)
        )
    }
}