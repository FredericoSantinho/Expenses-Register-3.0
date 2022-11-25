package neuro.expenses.register.ui.common.composables.text

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import neuro.expenses.register.ui.common.keyboard.keyboardOptionsNumeric
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme

@Composable
fun BasicNumericTextField(
  value: MutableState<String>,
  modifier: Modifier = Modifier,
  label: String = "",
  onValueChange: (String) -> Unit = { },
  textStyle: TextStyle = TextStyle.Default,
  keyboardOptions: KeyboardOptions = keyboardOptionsNumeric
) {
  val _label: @Composable (() -> Unit)? = if (label.isNotEmpty()) {
    { Text(label) }
  } else null
  TextFieldWithoutPadding(
    modifier = modifier,
    value = value.value,
    onValueChange = {
      value.value = it
      onValueChange(it)
    },
    singleLine = true,
    colors = TextFieldDefaults.textFieldColors(
      backgroundColor = Color.Transparent,
    ),
    keyboardOptions = keyboardOptions,
    textStyle = textStyle.copy(textAlign = TextAlign.End),
    label = _label,
    minHeight = 0.dp,
    contentPadding = 0.dp
  )
}

@Preview
@Composable
fun PreviewBasicNumericTextField() {
  val value = remember { mutableStateOf("1.30") }

  ExpensesRegisterTheme {
    BasicNumericTextField(value)
  }
}