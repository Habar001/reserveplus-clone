package ua.gov.reserveplus.design.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.gov.reserveplus.design.ButtonText
import ua.gov.reserveplus.design.Ink
import ua.gov.reserveplus.design.Radius
import ua.gov.reserveplus.design.Sand
import ua.gov.reserveplus.design.Size
import ua.gov.reserveplus.design.InkMuted
import ua.gov.reserveplus.design.Paper

@Composable
fun PrimaryButton(
    text: String,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(Size.button),
        shape = RoundedCornerShape(Radius.m),
        colors = ButtonDefaults.buttonColors(
            containerColor = Ink,
            contentColor = Sand,
            disabledContainerColor = InkMuted,
            disabledContentColor = Paper
        )
    ) {
        Text(text, style = ButtonText)
    }
}