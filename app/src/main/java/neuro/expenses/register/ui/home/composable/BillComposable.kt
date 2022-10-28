package neuro.expenses.register.ui.report.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.exchangebot.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.R
import neuro.expenses.register.ui.home.BillViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun BillComposable(
  modifier: Modifier = Modifier,
  billViewModel: BillViewModel = getViewModel()
) {
  Card(
    modifier = modifier
      .requiredHeight(80.dp)
      .fillMaxWidth(),
    elevation = 2.dp,
    backgroundColor = Color.White,
    shape = RoundedCornerShape(corner = CornerSize(8.dp))
  ) {
    ConstraintLayout(
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
      val (imageC, placeC, dateC, totalC, closeBillC) = createRefs()

      AsyncImage(
        modifier = Modifier
          .padding(start = 8.dp)
          .constrainAs(imageC) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
          }
          .size(64.dp)
          .clip(RoundedCornerShape(corner = CornerSize(8.dp))),
        url = billViewModel.imageUrl.value
      )
      Text(
        modifier = Modifier
          .constrainAs(placeC) {
            start.linkTo(imageC.end)
            end.linkTo(totalC.start)
            top.linkTo(parent.top)
          }, text = billViewModel.place.value, style = typography.h5, fontWeight = FontWeight.Bold
      )
      Row(modifier = Modifier.constrainAs(dateC) {
        start.linkTo(imageC.end)
        end.linkTo(totalC.start)
        bottom.linkTo(parent.bottom)
      }) {
        Text(
          text = billViewModel.time.value,
          style = typography.body2
        )
        Text(
          modifier = Modifier.padding(start = 8.dp),
          text = billViewModel.date.value,
          style = typography.body2
        )
      }
      Text(
        modifier = Modifier
          .constrainAs(totalC) {
            end.linkTo(closeBillC.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
          },
        text = billViewModel.total.value,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primary
      )
      if (billViewModel.isBillOpen.value) {
        IconButton(
          onClick = {

          }, modifier = Modifier
            .constrainAs(closeBillC) {
              end.linkTo(parent.end)
              top.linkTo(parent.top)
              bottom.linkTo(parent.bottom)
            }
            .padding(start = 8.dp)
        ) {
          Icon(
            painter = painterResource(id = R.drawable.ic_close_bill_24),
            contentDescription = null,
            tint = Color.Black
          )
        }
      } else {
        Divider(modifier = Modifier
          .constrainAs(closeBillC) {
            end.linkTo(parent.end)
          }
          .fillMaxHeight()
          .width(0.dp)
        )
      }
    }
  }
}

@Preview
@Composable
fun PreviewDateTimeComposable() {
  ExpensesRegisterTheme {
    BillComposable()
  }
}