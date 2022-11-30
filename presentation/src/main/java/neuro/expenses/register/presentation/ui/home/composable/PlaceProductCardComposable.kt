package neuro.expenses.register.presentation.ui.home.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import neuro.expenses.register.presentation.mocks.placeproduct.placeProductCardModelMock
import neuro.expenses.register.presentation.ui.common.composables.image.AsyncImage
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.home.IProductCardViewModel
import neuro.expenses.register.viewmodel.home.PlaceProductCardViewModel
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlaceProductCardComposable(productCardViewModel: IProductCardViewModel) {
  val roundedCornerShape = RoundedCornerShape(corner = CornerSize(4.dp))

  Row {
    Card(
      modifier = Modifier
        .semantics { testTag = ProductCardComposableTags.CARD }
        .clip(roundedCornerShape)
        .height(100.dp)
        .combinedClickable(
          onClick = { productCardViewModel.onCardClick() },
          onLongClick = { productCardViewModel.onCardLongClick() },
        ),
      elevation = 2.dp,
      backgroundColor = MaterialTheme.colors.background,
      shape = roundedCornerShape
    ) {
      ConstraintLayout(
        modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp)
      ) {
        val (descriptionC, categoryC, imageC, priceC) = createRefs()

        Text(
          text = productCardViewModel.description.value,
          modifier = Modifier
            .semantics { testTag = ProductCardComposableTags.PRODUCT_DESCRIPTION }
            .constrainAs(descriptionC) {
              start.linkTo(parent.start)
              top.linkTo(parent.top, margin = 4.dp)
              end.linkTo(imageC.start, margin = 8.dp)
              width = Dimension.fillToConstraints
            }
            .padding(top = 2.dp, start = 2.dp),
          style = MaterialTheme.typography.body2,
          maxLines = 3,
        )
        AsyncImage(modifier = Modifier
          .semantics { testTag = productCardViewModel.iconUrl.value }
          .constrainAs(imageC) {
            end.linkTo(parent.end)
            top.linkTo(parent.top)
          }
          .size(48.dp)
          .clip(RoundedCornerShape(corner = CornerSize(8.dp))),
          productCardViewModel.iconUrl.value)
        Text(
          text = productCardViewModel.categoryModel.value.name,
          modifier = Modifier
            .semantics { testTag = ProductCardComposableTags.CATEGORY }
            .constrainAs(categoryC) {
              start.linkTo(descriptionC.start)
              bottom.linkTo(parent.bottom, margin = 4.dp)
            }
            .padding(start = 2.dp, bottom = 2.dp),
          style = MaterialTheme.typography.caption,
          fontWeight = FontWeight.Bold,
          maxLines = 1,
        )
        Text(
          text = productCardViewModel.price.value,
          modifier = Modifier
            .semantics { testTag = ProductCardComposableTags.PRICE }
            .constrainAs(priceC) {
              end.linkTo(parent.end)
              bottom.linkTo(parent.bottom)
            }
            .padding(start = 2.dp, bottom = 2.dp),
          style = MaterialTheme.typography.body1,
          fontWeight = FontWeight.Bold,
          maxLines = 1,
        )
      }
    }
  }
}

@Preview
@Composable
fun PreviewDateTimeComposable() {
  val placeProductCardModel = placeProductCardModelMock()

  ExpensesRegisterTheme {
    PlaceProductCardComposable(
      PlaceProductCardViewModel(placeProductCardModel)
    )
  }
}

class ProductCardComposableTags {
  companion object {
    const val CARD = "card"
    const val PRODUCT_DESCRIPTION = "productDescription"
    const val CATEGORY = "category"
    const val PRICE = "price"
  }
}