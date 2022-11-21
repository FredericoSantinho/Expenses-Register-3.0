package neuro.expenses.register.ui.common.composables.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties
import neuro.expenses.register.R
import neuro.expenses.register.ui.common.composables.text.TextFieldWithoutPadding
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.search.SearchViewModel

@Composable
fun SearchWithDropdown(
  modifier: Modifier = Modifier,
  dataIn: List<SearchSuggestion>,
  take: Int = 30,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  onValueChange: (String) -> Unit = { },
  onSelectOption: () -> Unit = { },
  isError: MutableState<Boolean> = mutableStateOf(false),
  searchViewModel: SearchViewModel
) {
  val mutableDataIn = remember { mutableStateOf(dataIn) }

  SearchWithDropdown(
    modifier,
    mutableDataIn,
    take,
    keyboardOptions,
    onValueChange,
    onSelectOption,
    isError,
    searchViewModel
  )
}

@Composable
fun SearchWithDropdown(
  modifier: Modifier = Modifier,
  dataIn: State<List<SearchSuggestion>>,
  take: Int = 30,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  onValueChange: (String) -> Unit = { },
  onSelectOption: () -> Unit = { },
  isError: MutableState<Boolean> = mutableStateOf(false),
  searchViewModel: SearchViewModel
) {
  val dropDownOptions = remember { mutableStateOf(listOf<SearchSuggestion>()) }
  val dropDownExpanded = remember { mutableStateOf(false) }
  val onDismissRequest = { dropDownExpanded.value = false }

  var isErrorVar by rememberSaveable { isError }
  var textFieldSize by remember { mutableStateOf(Size.Zero) }
  val focusManager = LocalFocusManager.current


  fun onValueChanged(value: String) {
    dropDownExpanded.value = true
    dropDownOptions.value = dataIn.value.filter {
      it.filter(value)
    }.take(take)
  }

  Box(modifier = modifier) {
    TextFieldWithoutPadding(
      modifier = Modifier
        .onGloballyPositioned { coordinates ->
          textFieldSize = coordinates.size.toSize()
        }
        .onFocusChanged { focusState ->
          if (!focusState.isFocused)
            onDismissRequest()
        },
      keyboardOptions = keyboardOptions,
      value = searchViewModel.query.value,
      onValueChange = {
        isErrorVar = false
        searchViewModel.query.value = it
        onValueChanged(it)
        onValueChange(searchViewModel.query.value)
      },
      colors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = Color.White,
        disabledTextColor = Color.LightGray,
        backgroundColor = Color.Transparent,
        focusedBorderColor = Color.White,
        unfocusedBorderColor = Color.LightGray,
        disabledBorderColor = Color.Transparent
      ),
      singleLine = true,
      isError = isErrorVar,
      textStyle = TextStyle.Default.copy(
        fontSize = MaterialTheme.typography.body1.fontSize
      ),
      placeholder = {
        Text(
          text = stringResource(R.string.search),
          color = Color.LightGray,
          fontSize = MaterialTheme.typography.body1.fontSize
        )
      },
      trailingIcon = {
        IconButton({
          searchViewModel.onCloseSearchButton()
        }) {
          Icon(
            Icons.Filled.Close,
            contentDescription = "Close search Icon",
            tint = Color.LightGray
          )
        }
      }
    )

    MaterialTheme(
      colors = MaterialTheme.colors.copy(surface = Color.White),
      shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(0))
    ) {
      val modifierDropMenu = if (dropDownOptions.value.isEmpty()) Modifier else Modifier
        .heightIn(max = 300.dp)
        .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
      DropdownMenu(
        modifier = modifierDropMenu,
        expanded = dropDownExpanded.value,
        properties = PopupProperties(
          focusable = false,
          dismissOnBackPress = true,
          dismissOnClickOutside = true
        ),
        onDismissRequest = onDismissRequest
      ) {
        var currentItemClass: Class<Any> = Unit.javaClass
        dropDownOptions.value.forEach { searchSuggestion ->
          if (currentItemClass != searchSuggestion.javaClass) {
            searchSuggestion.titleComposable()
            currentItemClass = searchSuggestion.javaClass
          }
          DropdownMenuItem(onClick = {
            focusManager.clearFocus()
            searchViewModel.query.value = searchSuggestion.text()
            onValueChange(searchViewModel.query.value)
            searchSuggestion.onClick()
            onSelectOption()
          }, contentPadding = PaddingValues()) {
            Column(
              modifier = Modifier
                .height(48.dp)
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
            ) {
              searchSuggestion.composable()
            }
          }
        }
      }
    }
  }
}

@Preview
@Composable
fun PreviewSearchWithDropdown() {
  val dataIn = remember { mutableStateOf(emptyList<SearchSuggestion>()) }

  ExpensesRegisterTheme {
    SearchWithDropdown(
      dataIn = dataIn,
      searchViewModel = SearchViewModel()
    )
  }
}
