package ua.gov.reserveplus.design.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.gov.reserveplus.design.AccentOrange
import ua.gov.reserveplus.design.Caption
import ua.gov.reserveplus.design.Sand
import ua.gov.reserveplus.design.Space
import ua.gov.reserveplus.design.StripOlive

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TickerBar(text: String, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .background(StripOlive)
            .padding(horizontal = Space.l, vertical = Space.m),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("•", style = Caption, color = AccentOrange)
        Spacer(Modifier.width(Space.s))
        Text(
            text = text,
            style = Caption,
            color = Sand,
            maxLines = 1,
            modifier = Modifier.basicMarquee(
                iterations = Int.MAX_VALUE,
                initialDelayMillis = 800,
                velocity = 40.dp
            )
        )
    }
}