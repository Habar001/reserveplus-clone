package ua.gov.reserveplus.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.gov.reserveplus.design.AccentOrange
import ua.gov.reserveplus.design.Body
import ua.gov.reserveplus.design.BodySmall
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.InkMuted
import ua.gov.reserveplus.design.Paper
import ua.gov.reserveplus.design.Radius
import ua.gov.reserveplus.design.SandSunken
import ua.gov.reserveplus.design.Size
import ua.gov.reserveplus.design.Space

@Composable
fun RadioRow(
    title: String,
    subtitle: String? = null,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Paper, RoundedCornerShape(Radius.m))
            .clickable { onClick() }
            .padding(horizontal = Space.xl, vertical = Space.l),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(Size.radio)
                .border(
                    width = if (selected) 2.dp else Size.hairline,
                    color = if (selected) AccentOrange else InkMuted,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Box(Modifier.size(10.dp).background(AccentOrange, CircleShape))
            }
        }
        Spacer(Modifier.width(Space.l))
        Column(Modifier.weight(1f)) {
            Text(title, style = Body, color = Ink)
            if (subtitle != null) {
                Spacer(Modifier.height(Space.xs))
                Text(subtitle, style = BodySmall, color = InkMuted)
            }
        }
    }
}