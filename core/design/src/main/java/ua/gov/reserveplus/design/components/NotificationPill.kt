package ua.gov.reserveplus.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.gov.reserveplus.design.Caption
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.Paper
import ua.gov.reserveplus.design.R
import ua.gov.reserveplus.design.Radius

@Composable
fun NotificationPill(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(60.dp)
            .background(Paper, RoundedCornerShape(Radius.pill))
            .padding(horizontal = 28.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Сповіщення", style = Caption.copy(fontSize = 18.sp), color = Ink)
        Spacer(Modifier.width(10.dp))
        Icon(
            painter = painterResource(R.drawable.ic_notifications),
            contentDescription = null,
            tint = Ink,
            modifier = Modifier.size(26.dp)
        )
    }
}