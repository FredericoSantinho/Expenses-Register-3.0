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
  onSelectedOption: (Int) -> (Unit),
  selectedItemIndex: MutableState<Int>,
  value: MutableState<String>
) {
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
      value = value.value,
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
          selectedItemIndex.value = index
          expanded = false
        }) {
          Text(text = selectedOption)
        }
      }
    }
  }
}