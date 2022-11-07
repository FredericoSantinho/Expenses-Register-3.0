package neuro.expenses.register.ui.composables.text

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.exchangebot.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.ui.common.keyboard.keyboardOptionsNumeric
import neuro.expenses.register.ui.manual.register.composable.SuffixTransformation

@Composable
fun CurrencyTextField(
  label: String,
  modifier: Modifier = Modifier,
  symbol: String = "€",
  onValueChange: (String) -> Unit = { },
  value: MutableState<String> = mutableStateOf("")
) {
  TextField(
    value = value.value,
    onValueChange = {
      onValueChange(it)
    },
    label = { Text(label) },
    modifier = modifier,
    singleLine = true,
    colors = TextFieldDefaults.textFieldColors(
      backgroundColor = Color.Transparent,
    ),
    keyboardOptions = keyboardOptionsNumeric,
    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
    visualTransformation = SuffixTransformation(symbol)
  )
}

@Preview
@Composable
fun PreviewCurrencyTextField() {
  ExpensesRegisterTheme {
    CurrencyTextField("price")
  }
}