package neuro.expenses.register.presentation.ui.common.composables.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun DropdownButton(modifier: Modifier = Modifier, items: List<String>, onItemSelect: OnItemSelect) {
  var expanded by remember { mutableStateOf(false) }
  var selectedIndex by remember { mutableStateOf(0) }
  var textFieldSize by remember { mutableStateOf(Size.Zero) }
  val arrowIcon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

  Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
    Button(modifier = modifier
      .onGloballyPositioned { coordinates ->
        textFieldSize = coordinates.size.toSize()
      }, onClick = {
      expanded = !expanded
    }) {
      ConstraintLayout(
        modifier = Modifier
          .fillMaxWidth()
      ) {
        val (textC, iconC) = createRefs()

        Text(modifier = Modifier.constrainAs(textC) {
          start.linkTo(parent.start)
          linkTo(top = parent.top, bottom = parent.bottom)
        }, text = items[selectedIndex])
        Icon(arrowIcon, "contentDescription", Modifier.constrainAs(iconC) {
          end.linkTo(parent.end, margin = (-8).dp)
          linkTo(top = parent.top, bottom = parent.bottom)
        })
      }
    }
    DropdownMenu(
      expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier
        .background(
          Color.White
        )
        .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
    ) {
      items.forEachIndexed { index, s ->
        DropdownMenuItem(onClick = {
          selectedIndex = index
          expanded = false
          onItemSelect.onItemSelect(index)
        }) {
          Text(text = s)
        }
      }
    }
  }
}
