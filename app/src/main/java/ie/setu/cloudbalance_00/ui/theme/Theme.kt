package ie.setu.cloudbalance_00.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    background = BackgroundColor,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = TextColor,
    error = ErrorColor
)

@Composable
fun CloudBalanceTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = AppTypography,
        shapes = Shapes,
        content = content
    )
}
