package neuro.expenses.register.ui.bills

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import neuro.expenses.register.ui.common.composables.image.AsyncImage
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.edit.bill.model.BillItemModel

@Composable
fun BillItemComposable(billItemModel: BillItemModel, modifier: Modifier = Modifier) {
  Row(modifier = modifier.height(32.dp)) {
    Column(
      modifier = Modifier
        .fillMaxHeight()
        .weight(0.1f),
      verticalArrangement = Arrangement.Center
    ) {
      AsyncImage(
        modifier = Modifier
          .padding(start = 4.dp)
          .size(28.dp)
          .clip(RoundedCornerShape(corner = CornerSize(8.dp))), billItemModel.iconUrl
      )
    }

    Column(
      modifier = Modifier
        .fillMaxHeight()
        .weight(0.45f),
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        billItemModel.description,
        style = MaterialTheme.typography.subtitle2,
        textAlign = TextAlign.Left,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
      )
    }
    Column(
      modifier = Modifier
        .fillMaxHeight()
        .weight(0.15f),
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        billItemModel.price,
        style = MaterialTheme.typography.subtitle2,
        textAlign = TextAlign.Right,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth()
      )
    }
    Column(
      modifier = Modifier
        .fillMaxHeight()
        .weight(0.13f),
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        billItemModel.amount,
        style = MaterialTheme.typography.subtitle2,
        textAlign = TextAlign.Right,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth()
      )
    }
    Column(
      modifier = Modifier
        .fillMaxHeight()
        .weight(0.17f),
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        billItemModel.total,
        style = MaterialTheme.typography.subtitle2,
        textAlign = TextAlign.Right,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth()
      )
    }
  }
}

@Preview
@Composable
fun PreviewBillItemComposable() {
  val id = 0L
  val description = "Sagres Média 0,33cl"
  val price = "1.30 €"
  val amount = "2.00"
  val total = "2.60"
  val iconUrl = ""

  val billItemModel = BillItemModel(id, description, price, amount, total, iconUrl)

  ExpensesRegisterTheme {
    BillItemComposable(billItemModel)
  }
}
