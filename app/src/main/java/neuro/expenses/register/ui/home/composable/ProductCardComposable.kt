package neuro.expenses.register.ui.home.composable

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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.exchangebot.ui.theme.ExpensesRegisterTypography
import neuro.expenses.register.ui.report.composable.AsyncImage

@Composable
fun ProductCardComposable(description: String, category: String, imageUrl: String, price: String) {
  Row {
    Card(
      modifier = Modifier
        .padding(horizontal = 8.dp, vertical = 8.dp)
        .width(200.dp)
        .height(100.dp),
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
          text = description,
          modifier = Modifier
            .constrainAs(descriptionC) {
              start.linkTo(parent.start)
              top.linkTo(parent.top)
              end.linkTo(imageC.start)
              width = Dimension.fillToConstraints
            }
            .padding(top = 2.dp, start = 2.dp),
          fontSize = ExpensesRegisterTypography.caption.fontSize,
          maxLines = 3,
        )
        AsyncImage(modifier = Modifier
          .constrainAs(imageC) {
            end.linkTo(parent.end)
            top.linkTo(parent.top)
          }
          .size(48.dp)
          .clip(RoundedCornerShape(corner = CornerSize(8.dp))),
          imageUrl)
        Text(
          text = category,
          modifier = Modifier
            .constrainAs(categoryC) {
              start.linkTo(parent.start)
              bottom.linkTo(parent.bottom)
            }
            .padding(start = 2.dp, bottom = 2.dp),
          fontSize = ExpensesRegisterTypography.caption.fontSize,
          fontWeight = FontWeight.Bold,
          maxLines = 1,
        )
        Text(
          text = price,
          modifier = Modifier
            .constrainAs(priceC) {
              end.linkTo(parent.end)
              bottom.linkTo(parent.bottom)
            }
            .padding(start = 2.dp, bottom = 2.dp),
          fontSize = ExpensesRegisterTypography.body2.fontSize,
          fontWeight = FontWeight.Bold,
          maxLines = 1,
        )
      }
    }
  }
}