package neuro.expenses.register.ui.common.composables.appbar.suggestions.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import neuro.expenses.register.ui.common.composables.image.AsyncImage

@Composable
fun ProductSearchSuggestionComposable(description: String, price: String, iconUrl: String) {
  ConstraintLayout(
    modifier = Modifier.fillMaxSize()
  ) {
    val (iconC, descriptionC, priceC, dividerC) = createRefs()

    AsyncImage(modifier = Modifier
      .constrainAs(iconC) {
        linkTo(top = parent.top, bottom = parent.bottom)
        start.linkTo(parent.start, margin = 4.dp)
      }
      .size(28.dp)
      .clip(RoundedCornerShape(corner = CornerSize(8.dp))), iconUrl)

    Text(
      description,
      modifier = Modifier.constrainAs(descriptionC) {
        linkTo(top = parent.top, bottom = parent.bottom)
        start.linkTo(iconC.end, margin = 8.dp)
        end.linkTo(priceC.start, margin = 8.dp)
        width = Dimension.fillToConstraints
      },
      maxLines = 2,
      fontSize = MaterialTheme.typography.subtitle2.fontSize,
      overflow = TextOverflow.Ellipsis
    )
    Text(
      price,
      modifier = Modifier.constrainAs(priceC) {
        linkTo(top = parent.top, bottom = parent.bottom)
        end.linkTo(parent.end, margin = 4.dp)
      },
      fontSize = MaterialTheme.typography.subtitle2.fontSize,
      fontWeight = FontWeight.Bold,
      maxLines = 1,
    )
  }
}


@Composable
fun ProductSearchSuggestionTitleComposable() {
  Column {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
      Text(
        stringResource(neuro.expenses.register.R.string.search_products_title),
        fontWeight = FontWeight.Bold
      )
    }
    Divider(thickness = 2.dp, color = Color.LightGray)
  }
}