package neuro.expenses.register.presentation.ui.bills

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
import neuro.expenses.register.presentation.mocks.bill.billDetailedViewModelMock
import neuro.expenses.register.presentation.ui.common.composables.edit.SaveDeleteComposable
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.bill.BillDetailedViewModel
import neuro.expenses.register.viewmodel.bill.model.BillItemModel

@Composable
fun BillDetailedComposable(
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
      modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
      text = billDetailedViewModel.placeName.value,
      fontWeight = FontWeight.Bold,
      style = MaterialTheme.typography.h5,
    )
    Column {
      BillItemHeaderComposable()
      Divider(thickness = 1.dp, color = Color.Gray)
      LazyColumn(
        Modifier.heightIn(max = 360.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        items(billDetailedViewModel.billItems.value,
          { billItemModel: BillItemModel -> billItemModel.id }) { billItemViewModel ->
          BillItemComposable(billItemViewModel) { billDetailedViewModel.onBillItemsChange() }
          Divider(
            modifier = Modifier.padding(start = 4.dp, end = 4.dp),
            thickness = 1.dp,
            color = Color.Gray
          )
        }
      }
      BillItemFooterComposable(
        billDetailedViewModel.total.value, billDetailedViewModel.calendar
      )
    }
    SaveDeleteComposable({}, {})
  }
}

@Preview
@Composable
fun PreviewBillDetailedComposable() {
  val fragmentActivity = FragmentActivity()
  val billDetailedViewModel = billDetailedViewModelMock()

  ExpensesRegisterTheme {
    BillDetailedComposable(billDetailedViewModel)
  }
}