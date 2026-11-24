package ua.gov.reserveplus.design.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.gov.reserveplus.design.Caption
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.Space
import ua.gov.reserveplus.design.StripSand
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.MarqueeSpacing

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TickerBar(text: String, modifier: Modifier = Modifier, textSize: TextUnit = 12.sp) {
    Row(
        modifier
            .fillMaxWidth()
            .background(StripSand)
            .padding(horizontal = Space.xl, vertical = Space.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = Caption.copy(fontSize = textSize, fontWeight = FontWeight.Normal),
            color = Ink,
            maxLines = 1,
            modifier = Modifier.basicMarquee(
                iterations = Int.MAX_VALUE,
                velocity = 40.dp,
                initialDelayMillis = 800,
                spacing = MarqueeSpacing(0.dp)
            )
        )
    }
}