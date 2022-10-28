package neuro.expenses.register.ui.home.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.exchangebot.ui.theme.ExpensesRegisterTheme
import com.exchangebot.ui.theme.ExpensesRegisterTypography
import neuro.expenses.register.ui.home.ProductCardViewModel
import neuro.expenses.register.ui.report.composable.AsyncImage
import org.koin.androidx.compose.getViewModel

@Composable
fun ProductCardComposable(productCardViewModel: ProductCardViewModel = getViewModel()) {
  Row {
    Card(
      modifier = Modifier
        .height(100.dp)
        .clickable {
          productCardViewModel.onCardClick()
        },
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
          .constrainAs(imageC) {
            end.linkTo(parent.end)
            top.linkTo(parent.top)
          }
          .size(48.dp)
          .clip(RoundedCornerShape(corner = CornerSize(8.dp))),
          productCardViewModel.imageUrl.value)
        Text(
          text = productCardViewModel.category.value,
          modifier = Modifier
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
  ExpensesRegisterTheme {
    ProductCardComposable()
  }
}