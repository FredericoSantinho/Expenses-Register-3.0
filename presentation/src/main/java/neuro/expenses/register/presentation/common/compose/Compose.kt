package neuro.expenses.register.presentation.common.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberSaveableUnit(unit: () -> Unit) {
  rememberSaveable {
    unit()
    0
  }
}