package neuro.expenses.register.presentation.ui.common.composables.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import neuro.expenses.register.presentation.ui.common.keyboard.keyboardOptionsNumeric
import neuro.expenses.register.presentation.ui.manual.register.composable.SuffixTransformation
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTheme
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
  textStyle: TextStyle = TextStyle.Default,
  isError: MutableState<Boolean> = mutableStateOf(false),
  errorMessage: MutableState<String> = mutableStateOf(""),
  testTag: String = "",
  errorTestTag: String = ""
) {
  Column(modifier = modifier) {
    TextField(
      modifier = Modifier.testTag(testTag),
      value = value.value,
      onValueChange = {
        value.value = it
        onValueChange(it)
      },
      label = { Text(label) },
      singleLine = true,
      colors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.Transparent,
      ),
      keyboardOptions = keyboardOptionsNumeric,
      textStyle = textStyle.copy(textAlign = TextAlign.End),
      visualTransformation = SuffixTransformation(symbol)
    )
    if (isError.value && errorMessage.value.isNotEmpty()) {
      Text(
        text = errorMessage.value,
        color = MaterialTheme.colors.error,
        style = MaterialTheme.typography.caption,
        modifier = Modifier
          .testTag(errorTestTag)
          .padding(start = 16.dp)
      )
    }
  }
}

@Preview
@Composable
fun PreviewCurrencyTextField() {
  val value = remember { mutableStateOf("1.30") }

  ExpensesRegisterTheme {
    CurrencyTextField(value, "price", "€")
  }
}