package neuro.expenses.register.ui.common.composables.dropdown

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownTextField(
  modifier: Modifier = Modifier,
  color: Color = Color.Transparent,
  label: String = "",
  listItems: State<List<String>>,
  onSelectedOption: (Int) -> (Unit)
) {
  var selectedItem by remember {
    mutableStateOf(if (listItems.value.isNotEmpty()) listItems.value[0] else "")
  }
  var selectedItemIndex by remember {
    mutableStateOf(0)
  }

  var expanded by remember {
    mutableStateOf(false)
  }

  ExposedDropdownMenuBox(
    expanded = expanded,
    onExpandedChange = {
      expanded = !expanded
    }, modifier = modifier
  ) {
    TextField(
      value = selectedItem,
      onValueChange = {},
      readOnly = true,
      label = { Text(text = label) },
      singleLine = true,
      trailingIcon = {
        ExposedDropdownMenuDefaults.TrailingIcon(
          expanded = expanded
        )
      },
      colors = TextFieldDefaults.textFieldColors(
        backgroundColor = color,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
      )
    )

    ExposedDropdownMenu(
      expanded = expanded,
      onDismissRequest = { expanded = false }
    ) {
      listItems.value.forEachIndexed { index, selectedOption ->
        DropdownMenuItem(onClick = {
          onSelectedOption.invoke(index)
          selectedItemIndex = index
          expanded = false
        }) {
          Text(text = selectedOption)
        }
      }
    }
  }
  selectedItem = if (listItems.value.isNotEmpty()) listItems.value[selectedItemIndex] else ""
}