package ua.gov.reserveplus.design.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import ua.gov.reserveplus.design.ButtonText
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.LineStrong
import ua.gov.reserveplus.design.Paper
import ua.gov.reserveplus.design.Radius
import ua.gov.reserveplus.design.Size

@Composable
fun OutlineButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(Size.button),
        border = BorderStroke(Size.hairline, LineStrong),
        shape = RoundedCornerShape(Radius.m),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Paper,
            contentColor = Ink
        )
    ) {
        Text(text, style = ButtonText, textAlign = TextAlign.Center)
    }
}