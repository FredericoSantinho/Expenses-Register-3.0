package neuro.expenses.register.ui.home.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import neuro.expenses.register.ui.common.composables.image.AsyncImage
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.ui.theme.ExpensesRegisterTypography
import neuro.expenses.register.viewmodel.home.IProductCardViewModel
import neuro.expenses.register.viewmodel.home.OnProductCardClick
import neuro.expenses.register.viewmodel.home.ProductCardViewModel
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductCardComposable(productCardViewModel: IProductCardViewModel) {
  Row {
    Card(
      modifier = Modifier
        .semantics { testTag = ProductCardTags.CARD }
        .height(100.dp)
        .combinedClickable(
          onClick = { productCardViewModel.onCardClick() },
          onLongClick = { productCardViewModel.onCardLongClick() },
        ),
      elevation = 2.dp,
      backgroundColor = Color.White,
      shape = RoundedCornerShape(corner = CornerSize(4.dp))
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
            .semantics { testTag = ProductCardTags.PRODUCT_DESCRIPTION }
            .constrainAs(descriptionC) {
              start.linkTo(parent.start)
              top.linkTo(parent.top, margin = 4.dp)
              end.linkTo(imageC.start, margin = 8.dp)
              width = Dimension.fillToConstraints
            }
            .padding(top = 2.dp, start = 2.dp),
          fontSize = 15.sp,
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
          text = productCardViewModel.categoryName.value,
          modifier = Modifier
            .semantics { testTag = ProductCardTags.CATEGORY }
            .constrainAs(categoryC) {
              start.linkTo(descriptionC.start)
              bottom.linkTo(parent.bottom, margin = 4.dp)
            }
            .padding(start = 2.dp, bottom = 2.dp),
          fontSize = ExpensesRegisterTypography.caption.fontSize,
          fontWeight = FontWeight.Bold,
          maxLines = 1,
        )
        Text(
          text = productCardViewModel.price.value,
          modifier = Modifier
            .semantics { testTag = ProductCardTags.PRICE }
            .constrainAs(priceC) {
              end.linkTo(parent.end)
              bottom.linkTo(parent.bottom)
            }
            .padding(start = 2.dp, bottom = 2.dp),
          fontSize = ExpensesRegisterTypography.body1.fontSize,
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
  val description = "Tosta Mista Pâo Caseiro"
  val categoryId = 1L
  val categoryName = "Restau"
  val place = "Riviera"
  val price = "4.20 €"
  val amount = 1.0
  val iconUrl =
    "https://www.iguaria.com/wp-content/uploads/2016/03/Iguaria_Tosta-de-Bacon-Queijo-Fiambre.jpg"
  val productCardModel =
    ProductCardModel(0, description, categoryId, categoryName, place, price, iconUrl, amount)
  val calendar = remember { mutableStateOf(Calendar.getInstance()) }

  ExpensesRegisterTheme {
    ProductCardComposable(
      ProductCardViewModel(
        MockedOnProductCardClick(),
        productCardModel,
        calendar
      )
    )
  }
}

private class MockedOnProductCardClick : OnProductCardClick {
  override fun onProductCardClick(productCardModel: ProductCardModel, calendar: Calendar) {}

  override fun onProductCardLongClick(productCardModel: ProductCardModel, calendar: Calendar) {}
}

class ProductCardTags {
  companion object {
    const val CARD = "card"
    const val PRODUCT_DESCRIPTION = "productDescription"
    const val CATEGORY = "category"
    const val PRICE = "price"
  }
}