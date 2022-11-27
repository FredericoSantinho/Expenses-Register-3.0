package neuro.expenses.register.ui.bills

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import neuro.expenses.register.common.koin.startKoinIfNeeded
import neuro.expenses.register.di.registerExpensesModule
import neuro.expenses.register.mocks.bill.BillItemModelMock
import neuro.expenses.register.ui.common.composables.image.AsyncImage
import neuro.expenses.register.ui.common.composables.text.BasicCurrencyTextField
import neuro.expenses.register.ui.common.composables.text.BasicNumericTextField
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.bill.model.BillItemModel
import neuro.expenses.register.viewmodel.common.formatter.CurrencyFormatter
import neuro.expenses.register.viewmodel.di.viewModelModule
import org.koin.androidx.compose.get

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BillItemComposable(
  billItemModel: BillItemModel,
  modifier: Modifier = Modifier,
  currencyFormatter: CurrencyFormatter = get(),
  onValuesChange: () -> (Unit) = {}
) {
  val isEditing = rememberSaveable { mutableStateOf(false) }

  Row(
    modifier = modifier
      .height(32.dp)
      .padding(start = 4.dp, end = 4.dp)
      .combinedClickable(
        onClick = {},
        onLongClick = { isEditing.value = !isEditing.value },
      )
  ) {
    Column(
      modifier = Modifier
        .fillMaxHeight()
        .weight(0.1f), verticalArrangement = Arrangement.Center
    ) {
      AsyncImage(
        modifier = Modifier
          .size(28.dp)
          .clip(RoundedCornerShape(corner = CornerSize(8.dp))), billItemModel.iconUrl
      )
    }

    Column(
      modifier = Modifier
        .fillMaxHeight()
        .weight(0.45f), verticalArrangement = Arrangement.Center
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
        .weight(0.15f), verticalArrangement = Arrangement.Center
    ) {
      if (isEditing.value) {
        BasicCurrencyTextField(
          billItemModel.price,
          textStyle = MaterialTheme.typography.subtitle2,
          onValueChange = {
            billItemModel.updateTotal()
            onValuesChange()
          })
      } else {
        Text(
          currencyFormatter.format(billItemModel.price.value),
          style = MaterialTheme.typography.subtitle2,
          textAlign = TextAlign.Right,
          maxLines = 1,
          modifier = Modifier.fillMaxWidth(),
          overflow = TextOverflow.Ellipsis
        )
      }
    }
    Column(
      modifier = Modifier
        .fillMaxHeight()
        .weight(0.13f), verticalArrangement = Arrangement.Center
    ) {
      if (isEditing.value) {
        BasicNumericTextField(
          billItemModel.amount,
          textStyle = MaterialTheme.typography.subtitle2.copy(textAlign = TextAlign.End),
          onValueChange = {
            billItemModel.updateTotal()
            onValuesChange()
          },
          modifier = Modifier.padding(start = 8.dp)
        )
      } else {
        Text(
          billItemModel.amount.value,
          style = MaterialTheme.typography.subtitle2,
          textAlign = TextAlign.End,
          maxLines = 1,
          modifier = Modifier.fillMaxWidth(),
          overflow = TextOverflow.Ellipsis
        )
      }
    }
    Column(
      modifier = Modifier
        .fillMaxHeight()
        .weight(0.17f), verticalArrangement = Arrangement.Center
    ) {
      Text(
        currencyFormatter.format(billItemModel.total.value),
        style = MaterialTheme.typography.subtitle2,
        textAlign = TextAlign.Right,
        maxLines = 1,
        modifier = Modifier
          .fillMaxWidth()
          .padding(start = 4.dp),
        overflow = TextOverflow.Ellipsis
      )
    }
  }
}

@Preview
@Composable
fun PreviewBillItemComposable() {
  startKoinIfNeeded { modules(registerExpensesModule, viewModelModule) }

  val billItemViewModel = BillItemModelMock().createBillItemModel()

  ExpensesRegisterTheme {
    BillItemComposable(
      billItemViewModel
    )
  }
}
