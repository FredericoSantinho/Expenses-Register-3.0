package neuro.expenses.register.ui.composables.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.PopupProperties
import com.exchangebot.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.R

@Composable
fun TextFieldWithDropdown(
  dataIn: List<String>,
  label: String = "",
  take: Int = 3,
  modifier: Modifier = Modifier,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default
): SetError {

  val dropDownOptions = remember { mutableStateOf(listOf<String>()) }
  val textFieldValue = remember { mutableStateOf(TextFieldValue()) }
  val dropDownExpanded = remember { mutableStateOf(false) }

  fun onDropdownDismissRequest() {
    dropDownExpanded.value = false
  }

  fun onValueChanged(value: TextFieldValue) {
    dropDownExpanded.value = true
    textFieldValue.value = value
    dropDownOptions.value = dataIn.filter {
      it.startsWith(value.text) && it != value.text
    }.take(take)
  }

  return TextFieldWithDropdown(
    modifier = modifier,
    textFieldValue,
    setValue = ::onValueChanged,
    onDismissRequest = ::onDropdownDismissRequest,
    dropDownExpanded = dropDownExpanded.value,
    list = dropDownOptions.value,
    label = label,
    keyboardOptions
  )
}

@Composable
fun TextFieldWithDropdown(
  modifier: Modifier = Modifier,
  value: MutableState<TextFieldValue>,
  setValue: (TextFieldValue) -> Unit,
  onDismissRequest: () -> Unit,
  dropDownExpanded: Boolean,
  list: List<String>,
  label: String = "",
  keyboardOptions: KeyboardOptions
): SetError {
  var isErrorVar by rememberSaveable { mutableStateOf(false) }

  Box(modifier) {
    TextField(
      modifier = Modifier
        .fillMaxWidth()
        .onFocusChanged { focusState ->
          if (!focusState.isFocused)
            onDismissRequest()
        },
      keyboardOptions = keyboardOptions,
      value = value.value,
      onValueChange = {
        isErrorVar = false
        setValue.invoke(it)
      },
      label = { Text(label) },
      colors = TextFieldDefaults.outlinedTextFieldColors(),
      singleLine = true,
      isError = isErrorVar
    )
    DropdownMenu(
      expanded = dropDownExpanded,
      properties = PopupProperties(
        focusable = false,
        dismissOnBackPress = true,
        dismissOnClickOutside = true
      ),
      onDismissRequest = onDismissRequest
    ) {
      list.forEach { text ->
        DropdownMenuItem(onClick = {
          setValue(
            TextFieldValue(
              text,
              TextRange(text.length)
            )
          )
        }) {
          Text(text = text)
        }
      }
    }
  }
  return object : SetError {
    override fun setError(isError: Boolean) {
      isErrorVar = isError
    }

    override fun setText(s: String) {
      value.value = TextFieldValue(s)
    }

    override fun getText(): String {
      return value.value.text
    }
  }
}

@Preview
@Composable
fun PreviewTextFieldWithDropdown() {
  ExpensesRegisterTheme {
    TextFieldWithDropdown(
      dataIn = listOf("aaa", "abb", "abc"),
      label = stringResource(R.string.manual_register_category)
    )
  }
}
