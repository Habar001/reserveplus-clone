package ua.gov.reserveplus.design.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.LineSoft
import ua.gov.reserveplus.design.Size
import androidx.compose.foundation.layout.size

@Composable
fun StatusCircle(icon: Painter, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(104.dp)
            .border(Size.hairline, LineSoft, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, null, tint = Ink, modifier = Modifier.width(39.dp).height(46.dp))
    }
}