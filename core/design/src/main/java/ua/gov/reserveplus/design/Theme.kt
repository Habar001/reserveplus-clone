package ua.gov.reserveplus.design

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val Scheme = lightColorScheme(
    background = Sand,
    surface = Sand,
    surfaceVariant = SandRaised,
    onBackground = Ink,
    onSurface = Ink,
    onSurfaceVariant = InkSecondary,
    primary = Ink,
    onPrimary = Sand,
    secondary = AccentOrange,
    outline = LineStrong,
    outlineVariant = LineSoft,
    error = DangerRed
)

private val SmallShape = RoundedCornerShape(Radius.s)
private val MediumShape = RoundedCornerShape(Radius.m)
private val LargeShape = RoundedCornerShape(Radius.card)

@Composable
fun ReserveTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = Scheme,
        typography = ReserveTypography,
        shapes = MaterialTheme.shapes.copy(
            extraSmall = SmallShape,
            small = SmallShape,
            medium = MediumShape,
            large = LargeShape,
            extraLarge = LargeShape
        )
    ) {
        Box(Modifier.fillMaxSize().background(Sand)) { content() }
    }
}