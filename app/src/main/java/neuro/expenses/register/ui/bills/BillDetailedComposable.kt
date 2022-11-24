package neuro.expenses.register.ui.bills

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import neuro.expenses.register.ui.common.composables.edit.SaveDeleteComposable
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.bills.BillDetailedViewModel
import neuro.expenses.register.viewmodel.common.formatter.CurrencyFormatterImpl
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatterImpl
import neuro.expenses.register.viewmodel.edit.bill.mapper.BillItemViewModelMapper
import neuro.expenses.register.viewmodel.edit.bill.model.BillItemViewModel

@Composable
fun BillDetailedComposable(
  fragmentActivity: FragmentActivity,
  billDetailedViewModel: BillDetailedViewModel,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .background(Color.White)
      .padding(8.dp)
      .fillMaxWidth(),
    horizontalAlignment = CenterHorizontally
  ) {
    Text(
      modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
      text = billDetailedViewModel.placeName.value,
      fontWeight = FontWeight.Bold,
      style = MaterialTheme.typography.h5,
    )
    Column {
      BillItemHeaderComposable()
      Divider(thickness = 1.dp, color = Color.Gray)
      LazyColumn(
        Modifier
          .heightIn(max = 360.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        items(
          billDetailedViewModel.billItems.value,
          { billItemViewModel: BillItemViewModel -> billItemViewModel.id }) { billItemViewModel ->
          BillItemComposable(billItemViewModel) { billDetailedViewModel.onBillItemsChange() }
          Divider(
            modifier = Modifier.padding(start = 4.dp, end = 4.dp),
            thickness = 1.dp,
            color = Color.Gray
          )
        }
      }
      BillItemFooterComposable(
        fragmentActivity,
        billDetailedViewModel.total.value,
        billDetailedViewModel.calendar
      )
    }
    SaveDeleteComposable({}, {})
  }
}

@Preview
@Composable
fun PreviewBillDetailedComposable() {
  val decimalFormatter = DecimalFormatterImpl(2)
  val currencyFormatter = CurrencyFormatterImpl(decimalFormatter, "€")
  val billItemViewModelMapper = BillItemViewModelMapper(decimalFormatter)

  ExpensesRegisterTheme {
    val billDetailedViewModel = BillDetailedViewModel(billItemViewModelMapper, currencyFormatter)
    billDetailedViewModel.placeName.value = "Bitoque"
    billDetailedViewModel.billItems.value = emptyList()

    BillDetailedComposable(FragmentActivity(), billDetailedViewModel)
  }
}