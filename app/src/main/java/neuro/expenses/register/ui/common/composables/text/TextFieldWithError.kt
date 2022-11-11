package neuro.expenses.register.ui.common.composables.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme

@Composable
fun TextFieldWithError(
  label: String,
  modifier: Modifier = Modifier,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  textStyle: TextStyle = TextStyle.Default,
  onValueChange: (String) -> Unit = { },
  value: MutableState<String> = mutableStateOf(""),
  isError: MutableState<Boolean> = mutableStateOf(false),
  errorMessage: MutableState<String> = mutableStateOf("")
) {
  Column(modifier = modifier) {
    TextField(
      value = value.value,
      onValueChange = {
        value.value = it
        onValueChange.invoke(it)
        isError.value = false
      },
      label = { Text(label) },
      singleLine = true,
      colors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.Transparent,
      ),
      keyboardOptions = keyboardOptions,
      textStyle = textStyle,
      modifier = Modifier.fillMaxWidth(),
      isError = isError.value
    )
    if (isError.value && errorMessage.value.isNotEmpty())
      Text(
        text = errorMessage.value,
        color = MaterialTheme.colors.error,
        style = MaterialTheme.typography.caption,
        modifier = Modifier.padding(start = 16.dp)
      )
  }
}

@Preview
@Composable
fun PreviewTextFieldWithError() {
  ExpensesRegisterTheme {
    TextFieldWithError("description")
  }
}