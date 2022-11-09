package neuro.expenses.register.ui.common.bill

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
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
import neuro.expenses.register.ui.report.composable.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BillComposable(
  billViewModel: BillViewModel,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .requiredHeight(80.dp)
      .fillMaxWidth()
      .combinedClickable(
        onClick = { billViewModel.onClick() },
        onLongClick = { billViewModel.onLongClick() },
      ),
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
        url = billViewModel.iconUrl.value
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
      val iconConstraintModifier = Modifier
        .constrainAs(closeBillC) {
          end.linkTo(parent.end)
          top.linkTo(parent.top)
          bottom.linkTo(parent.bottom)
        }
      if (billViewModel.isEdit.value) {
        editBillIcon(iconConstraintModifier, billViewModel)
      } else {
        if (billViewModel.isBillOpen.value) {
          closeBillIcon(iconConstraintModifier)
        } else {
          guideDivider(iconConstraintModifier)
        }
      }
    }
  }
}

@Composable
private fun guideDivider(iconConstraintModifier: Modifier) {
  Divider(
    modifier = iconConstraintModifier
      .fillMaxHeight()
      .width(0.dp)
  )
}

@Composable
private fun closeBillIcon(imageConstraintModifier: Modifier) {
  IconButton(
    onClick = {

    }, modifier = imageConstraintModifier
      .padding(start = 8.dp)
  ) {
    Icon(
      painter = painterResource(id = R.drawable.ic_close_bill_24),
      contentDescription = null,
      tint = Color.Black
    )
  }
}

@Composable
private fun editBillIcon(imageConstraintModifier: Modifier, billViewModel: BillViewModel) {
  IconButton(
    onClick = {
      billViewModel.onEditClick()
    }, modifier = imageConstraintModifier
      .padding(start = 8.dp)
  ) {
    Icon(
      painter = painterResource(id = R.drawable.ic_edit_24),
      contentDescription = null,
      tint = Color.Black
    )
  }
}

@Preview
@Composable
fun PreviewDateTimeComposable() {
  ExpensesRegisterTheme {
    BillComposable(BillViewModel())
  }
}