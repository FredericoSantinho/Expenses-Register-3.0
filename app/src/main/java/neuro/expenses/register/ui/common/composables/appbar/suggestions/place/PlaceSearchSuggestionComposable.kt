package neuro.expenses.register.ui.common.composables.appbar.suggestions.place

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun PlaceSearchSuggestionComposable(name: String) {
  ConstraintLayout(
    modifier = Modifier.fillMaxSize()
  ) {
    val (iconC, nameC) = createRefs()

    Column(modifier = Modifier
      .constrainAs(iconC) {
        linkTo(top = parent.top, bottom = parent.bottom)
        start.linkTo(parent.start, margin = 4.dp)
      }
      .size(28.dp), verticalArrangement = Arrangement.Center) {
      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Icon(
          Icons.Filled.LocationOn,
          null,
          tint = MaterialTheme.colors.primary,
          modifier = Modifier
            .size(16.dp)
        )
      }
    }

    Text(
      name,
      modifier = Modifier.constrainAs(nameC) {
        linkTo(top = parent.top, bottom = parent.bottom)
        start.linkTo(iconC.end, margin = 8.dp)
        width = Dimension.fillToConstraints
      },
      maxLines = 2,
      style = MaterialTheme.typography.subtitle2,
      overflow = TextOverflow.Ellipsis
    )
  }
}


@Composable
fun PlaceSearchSuggestionTitleComposable() {
  Column {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
      Text(
        stringResource(neuro.expenses.register.R.string.search_place_title),
        fontWeight = FontWeight.Bold
      )
    }
    Divider(thickness = 2.dp, color = Color.LightGray)
  }
}