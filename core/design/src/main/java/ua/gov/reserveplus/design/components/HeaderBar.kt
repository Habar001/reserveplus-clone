package ua.gov.reserveplus.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.Space

@Composable
fun HeaderBar(menuIcon: Painter, onMenu: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = Space.screen),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BrandLockup()
        Icon(
            painter = menuIcon,
            contentDescription = null,
            tint = Ink,
            modifier = Modifier.size(22.dp)
        )
    }
}