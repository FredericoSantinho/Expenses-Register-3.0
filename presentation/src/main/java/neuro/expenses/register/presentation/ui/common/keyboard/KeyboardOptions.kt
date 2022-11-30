package neuro.expenses.register.presentation.ui.common.keyboard

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

val keyboardOptionsNumeric = KeyboardOptions.Default.copy(
  keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
)

val keyboardOptionsText = KeyboardOptions.Default.copy(
  keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
)