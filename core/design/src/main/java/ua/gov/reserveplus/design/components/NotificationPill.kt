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
import ua.gov.reserveplus.design.Caption
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.Paper
import ua.gov.reserveplus.design.R
import ua.gov.reserveplus.design.Radius
import ua.gov.reserveplus.design.Space

@Composable
fun NotificationPill(count: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(40.dp)
            .background(Paper, RoundedCornerShape(Radius.pill))
            .padding(horizontal = Space.l),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Сповіщення", style = Caption, color = Ink)
        Spacer(Modifier.width(Space.s))
        Icon(
            painter = painterResource(R.drawable.ic_notifications),
            contentDescription = null,
            tint = Ink,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(Space.xs))
        Text(count, style = Caption, color = Ink)
    }
}