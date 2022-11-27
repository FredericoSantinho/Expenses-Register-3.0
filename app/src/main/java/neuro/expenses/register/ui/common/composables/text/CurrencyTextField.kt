package neuro.expenses.register.ui.common.composables.text

import androidx.compose.material.Text
import androidx.compose.material.TextField
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
import neuro.expenses.register.ui.common.keyboard.keyboardOptionsNumeric
import neuro.expenses.register.ui.manual.register.composable.SuffixTransformation
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.di.CURRENCY
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun CurrencyTextField(
  value: MutableState<String> = mutableStateOf(""),
  label: String,
  symbol: String = get(named(CURRENCY)),
  modifier: Modifier = Modifier,
  onValueChange: (String) -> Unit = { },
  textStyle: TextStyle = TextStyle.Default
) {
  TextField(
    value = value.value,
    onValueChange = {
      value.value = it
      onValueChange(it)
    },
    label = { Text(label) },
    modifier = modifier,
    singleLine = true,
    colors = TextFieldDefaults.textFieldColors(
      backgroundColor = Color.Transparent,
    ),
    keyboardOptions = keyboardOptionsNumeric,
    textStyle = textStyle.copy(textAlign = TextAlign.End),
    visualTransformation = SuffixTransformation(symbol)
  )
}

@Preview
@Composable
fun PreviewCurrencyTextField() {
  val value = remember { mutableStateOf("1.30") }

  ExpensesRegisterTheme {
    CurrencyTextField(value, "price", "€")
  }
}