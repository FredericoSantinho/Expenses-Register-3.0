package neuro.expenses.register.ui.composables.text

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CurrencyTextField(
  label: String,
  modifier: Modifier,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  symbol: String = "€",
  onValueChange: (String) -> Unit = { }
): GetText {
  var text by rememberSaveable { mutableStateOf("") }

  TextField(
    value = text,
    onValueChange = {
      if (it.endsWith(" $symbol")) {
        text = it
        onValueChange.invoke(it.substring(0, it.length - 2))
      } else {
        text = it + " $symbol"
        onValueChange.invoke(it)
      }
    },
    label = { Text(label) },
    modifier = modifier,
    singleLine = true,
    colors = TextFieldDefaults.textFieldColors(
      backgroundColor = Color.Transparent,
    ),
    keyboardOptions = keyboardOptions,
    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End)
  )

  return object : GetText {
    override fun getText(): String {
      return text
    }
  }
}