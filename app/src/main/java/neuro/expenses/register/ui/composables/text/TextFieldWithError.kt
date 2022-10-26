package neuro.expenses.register.ui.composables.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.exchangebot.ui.theme.ExpensesRegisterTheme

@Composable
fun TextFieldWithError(
  label: String,
  modifier: Modifier = Modifier,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  textStyle: TextStyle = TextStyle.Default,
  onValueChange: (String) -> Unit = { }
): SetErrorMessage {
  var text by rememberSaveable { mutableStateOf("") }
  var isErrorVar by rememberSaveable { mutableStateOf(false) }
  var errorMessageVar by rememberSaveable { mutableStateOf("") }

  Column(modifier = modifier) {
    TextField(
      value = text,
      onValueChange = {
        text = it
        onValueChange.invoke(it)
        isErrorVar = false
      },
      label = { Text(label) },
      singleLine = true,
      colors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.Transparent,
      ),
      keyboardOptions = keyboardOptions,
      textStyle = textStyle,
      modifier = Modifier.fillMaxWidth(),
      isError = isErrorVar
    )
    if (isErrorVar)
      Text(
        text = if (isErrorVar) errorMessageVar else "",
        color = MaterialTheme.colors.error,
        style = MaterialTheme.typography.caption,
        modifier = Modifier.padding(start = 16.dp)
      )
  }

  return object : SetErrorMessage {
    override fun setError(isError: Boolean) {
      isErrorVar = isError
    }

    override fun setError(errorMessage: String) {
      errorMessageVar = errorMessage
      setError(true)
    }

    override fun setText(s: String) {
      text = s
    }
  }
}

@Preview
@Composable
fun PreviewTextFieldWithError() {
  ExpensesRegisterTheme {
    TextFieldWithError("description")
  }
}