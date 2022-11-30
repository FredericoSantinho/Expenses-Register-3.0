package neuro.expenses.register.presentation.common.compose

import androidx.compose.runtime.Composable

@Composable
fun rememberUnit(unit: () -> Unit) {
  androidx.compose.runtime.remember {
    unit()
    0
  }
}