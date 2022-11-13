package neuro.expenses.register.ui.common.composables.text

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import neuro.expenses.register.ui.common.keyboard.keyboardOptionsNumeric
import neuro.expenses.register.ui.manual.register.composable.SuffixTransformation
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme

@Composable
fun CurrencyTextField(
  label: String,
  modifier: Modifier = Modifier,
  symbol: String = "€",
  onValueChange: (String) -> Unit = { },
  value: MutableState<String> = mutableStateOf(""),
  textStyle: TextStyle = TextStyle.Default
) {
  var applyTransformation by remember { mutableStateOf(true) }

  TextField(
    value = value.value,
    onValueChange = {
      value.value = it
      onValueChange(it)
    },
    label = { Text(label) },
    modifier = modifier.onFocusChanged {
      applyTransformation = !it.isFocused
    },
    singleLine = true,
    colors = TextFieldDefaults.textFieldColors(
      backgroundColor = Color.Transparent,
    ),
    keyboardOptions = keyboardOptionsNumeric,
    textStyle = textStyle.copy(textAlign = TextAlign.End),
    visualTransformation = if (applyTransformation) SuffixTransformation(symbol) else VisualTransformation.None
  )
}

@Preview
@Composable
fun PreviewCurrencyTextField() {
  ExpensesRegisterTheme {
    CurrencyTextField("price")
  }
}