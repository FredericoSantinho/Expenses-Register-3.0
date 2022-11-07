package neuro.expenses.register.ui.composables.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.PopupProperties
import com.exchangebot.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.R

@Composable
fun TextFieldWithDropdown(
  modifier: Modifier = Modifier,
  dataIn: State<List<String>>,
  label: String = "",
  take: Int = 3,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  onValueChange: (String) -> Unit = { },
  value: MutableState<String> = mutableStateOf(""),
  isError: MutableState<Boolean> = mutableStateOf(false)
) {

  val dropDownOptions = remember { mutableStateOf(listOf<String>()) }
  val dropDownExpanded = remember { mutableStateOf(false) }

  fun onDropdownDismissRequest() {
    dropDownExpanded.value = false
  }

  fun onValueChanged(value: String) {
    dropDownExpanded.value = value.isNotBlank()
    dropDownOptions.value = dataIn.value.filter {
      it.startsWith(value) && it != value
    }.take(take)
    onValueChange.invoke(value)
  }

  val focusManager = LocalFocusManager.current
  TextFieldWithDropdown(
    modifier = modifier,
    setValue = { onValueChanged(it) },
    onDismissRequest = ::onDropdownDismissRequest,
    dropDownExpanded = dropDownExpanded.value,
    list = dropDownOptions.value,
    label = label,
    keyboardOptions,
    value,
    isError,
    focusManager
  )
}

@Composable
fun TextFieldWithDropdown(
  modifier: Modifier = Modifier,
  setValue: (String) -> Unit,
  onDismissRequest: () -> Unit,
  dropDownExpanded: Boolean,
  list: List<String>,
  label: String = "",
  keyboardOptions: KeyboardOptions,
  value: MutableState<String>,
  isError: MutableState<Boolean>,
  focusManager: FocusManager
) {
  var isErrorVar by rememberSaveable { isError }

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
        value.value = it
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
          value.value = text
          setValue(value.value)
          focusManager.moveFocus(FocusDirection.Next)
        }) {
          Text(text = text)
        }
      }
    }
  }
}

@Preview
@Composable
fun PreviewTextFieldWithDropdown() {
  val dataIn = remember { mutableStateOf(listOf("aaa", "abb", "abc")) }

  ExpensesRegisterTheme {
    TextFieldWithDropdown(
      dataIn = dataIn,
      label = stringResource(R.string.manual_register_category)
    )
  }
}
