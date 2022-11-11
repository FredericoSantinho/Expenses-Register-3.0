package neuro.expenses.register.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
  primary = primaryColor,
  primaryVariant = primaryDarkColor,
  secondary = secondaryColor,
  surface = surface,
  background = Color.White
)

private val LightColorPalette = lightColors(
  primary = primaryColor,
  primaryVariant = primaryDarkColor,
  secondary = secondaryColor,
  surface = surface,
  background = Color.Black

  /* Other default colors to override
  background = Color.White,
  surface = Color.White,
  onPrimary = Color.White,
  onSecondary = Color.Black,
  onBackground = Color.Black,
  onSurface = Color.Black,
  */
)

@Composable
fun ExpensesRegisterTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable() () -> Unit
) {
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  MaterialTheme(
    colors = colors,
    typography = ExpensesRegisterTypography,
    shapes = Shapes,
    content = content
  )
}