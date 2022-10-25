package neuro.expenses.register.ui.composables.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.PopupProperties

@Composable
fun TextFieldWithDropdown(
  dataIn: List<String>,
  label: String = "",
  take: Int = 3,
  modifier: Modifier,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default
): GetText {

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

  TextFieldWithDropdown(
    modifier = modifier,
    value = textFieldValue.value,
    setValue = ::onValueChanged,
    onDismissRequest = ::onDropdownDismissRequest,
    dropDownExpanded = dropDownExpanded.value,
    list = dropDownOptions.value,
    label = label,
    keyboardOptions
  )
  return object : GetText {
    override fun getText(): String {
      return textFieldValue.value.text
    }
  }
}

@Composable
fun TextFieldWithDropdown(
  modifier: Modifier = Modifier,
  value: TextFieldValue,
  setValue: (TextFieldValue) -> Unit,
  onDismissRequest: () -> Unit,
  dropDownExpanded: Boolean,
  list: List<String>,
  label: String = "",
  keyboardOptions: KeyboardOptions
) {
  Box(modifier) {
    TextField(
      modifier = Modifier
        .fillMaxWidth()
        .onFocusChanged { focusState ->
          if (!focusState.isFocused)
            onDismissRequest()
        },
      keyboardOptions = keyboardOptions,
      value = value,
      onValueChange = setValue,
      label = { Text(label) },
      colors = TextFieldDefaults.outlinedTextFieldColors(),
      singleLine = true
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
}
