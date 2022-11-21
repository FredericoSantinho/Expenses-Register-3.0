package neuro.expenses.register.ui.manual.register.composable

import androidx.compose.runtime.Composable

@Composable
fun rememberUnit(unit: () -> Unit) {
  androidx.compose.runtime.remember {
    unit()
    0
  }
}