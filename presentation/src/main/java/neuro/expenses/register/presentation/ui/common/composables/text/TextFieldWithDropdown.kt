package neuro.expenses.register.presentation.ui.common.composables.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import neuro.expenses.register.presentation.R
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTheme

@Composable
fun TextFieldWithDropdown(
  modifier: Modifier = Modifier,
  dataIn: State<List<String>>,
  label: String = "",
  take: Int = 3,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  onValueChange: (String) -> Unit = { },
  onSelectOption: () -> Unit = { },
  value: MutableState<String> = mutableStateOf(""),
  isError: MutableState<Boolean> = mutableStateOf(false),
  errorMessage: MutableState<String> = mutableStateOf(""),
  textStyle: TextStyle = TextStyle.Default,
  semantics: String = "",
  errorSemantics: String = ""
) {

  val dropDownOptions = remember { mutableStateOf(listOf<String>()) }
  val dropDownExpanded = remember { mutableStateOf(false) }

  fun onDropdownDismissRequest() {
    dropDownExpanded.value = false
  }

  fun onValueChanged(value: String) {
    dropDownExpanded.value = value.isNotBlank()
    dropDownOptions.value = dataIn.value.filter {
      it.lowercase().contains(value.lowercase()) && it != value
    }.take(take)
  }

  InternalTextFieldWithDropdown(
    modifier = modifier,
    setValue = { onValueChanged(it) },
    onDismissRequest = ::onDropdownDismissRequest,
    onValueChange = onValueChange,
    onSelectOption = onSelectOption,
    dropDownExpanded = dropDownExpanded.value,
    list = dropDownOptions.value,
    label = label,
    keyboardOptions = keyboardOptions,
    value = value,
    isError = isError,
    errorMessage = errorMessage,
    textStyle = textStyle,
    testTag = semantics,
    errorTestTag = errorSemantics
  )
}

@Composable
fun InternalTextFieldWithDropdown(
  modifier: Modifier = Modifier,
  setValue: (String) -> Unit,
  onDismissRequest: () -> Unit,
  onValueChange: (String) -> Unit,
  onSelectOption: () -> Unit,
  dropDownExpanded: Boolean,
  list: List<String>,
  label: String = "",
  keyboardOptions: KeyboardOptions,
  value: MutableState<String>,
  isError: MutableState<Boolean>,
  errorMessage: MutableState<String> = mutableStateOf(""),
  textStyle: TextStyle,
  testTag: String,
  errorTestTag: String
) {
  var isErrorVar by rememberSaveable { isError }

  Box(modifier) {
    Column {
      TextField(modifier = Modifier
        .semantics {
          this.testTag = testTag
        }
        .fillMaxWidth()
        .onFocusChanged { focusState ->
          if (!focusState.isFocused) onDismissRequest()
        },
        keyboardOptions = keyboardOptions,
        value = TextFieldValue(value.value, TextRange(value.value.length)),
        onValueChange = {
          isErrorVar = false
          value.value = it.text
          setValue.invoke(it.text)
          onValueChange.invoke(value.value)
        },
        label = { Text(label) },
        colors = TextFieldDefaults.outlinedTextFieldColors(),
        singleLine = true,
        isError = isErrorVar,
        textStyle = textStyle
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
    DropdownMenu(
      expanded = dropDownExpanded, properties = PopupProperties(
        focusable = false, dismissOnBackPress = true, dismissOnClickOutside = true
      ), onDismissRequest = onDismissRequest
    ) {
      list.forEach { text ->
        DropdownMenuItem(modifier = Modifier.testTag(testTag + text), onClick = {
          value.value = text
          onValueChange.invoke(value.value)
          onSelectOption()
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
  val dataIn = remember { mutableStateOf(listOf("Super", "Café", "Borga")) }

  ExpensesRegisterTheme {
    TextFieldWithDropdown(
      dataIn = dataIn, label = stringResource(R.string.category)
    )
  }
}
