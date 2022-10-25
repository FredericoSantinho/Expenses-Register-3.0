package neuro.expenses.register.ui.composables.text

import androidx.compose.foundation.text.KeyboardOptions
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

@Composable
fun TextField(
  label: String,
  modifier: Modifier,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  textStyle: TextStyle = TextStyle.Default,
  onValueChange: (String) -> Unit = { }
): SetText {
  var text by rememberSaveable { mutableStateOf("") }

  TextField(
    value = text,
    onValueChange = {
      text = it
      onValueChange.invoke(it)
    },
    label = { Text(label) },
    modifier = modifier,
    singleLine = true,
    colors = TextFieldDefaults.textFieldColors(
      backgroundColor = Color.Transparent,
    ),
    keyboardOptions = keyboardOptions,
    textStyle = textStyle
  )

  return object : SetText {
    override fun setText(s: String) {
      text = s
    }

    override fun getText(): String {
      return text
    }
  }
}