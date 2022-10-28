package neuro.expenses.register.ui.composable

import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownMenu(
  modifier: Modifier = Modifier,
  color: Color = Color.Transparent,
  label: String = "",
  listItems: State<List<String>>
) {
  val contextForToast = LocalContext.current.applicationContext

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
      )
    )

    ExposedDropdownMenu(
      expanded = expanded,
      onDismissRequest = { expanded = false }
    ) {
      listItems.value.forEach { selectedOption ->
        DropdownMenuItem(onClick = {
          selectedItem = selectedOption
          Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
          expanded = false
        }) {
          Text(text = selectedOption)
        }
      }
    }
  }
}