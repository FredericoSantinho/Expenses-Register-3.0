package neuro.expenses.register.ui.composable

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownMenu(
  modifier: Modifier = Modifier,
  color: Color = Color.Transparent,
  label: String = "",
  listItems: State<List<String>>
) {
  var selectedItem by remember {
    mutableStateOf(listItems.value[0])
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
      listItems.value.forEach { selectedOption ->
        DropdownMenuItem(onClick = {
          selectedItem = selectedOption
          expanded = false
        }) {
          Text(text = selectedOption)
        }
      }
    }
  }
}