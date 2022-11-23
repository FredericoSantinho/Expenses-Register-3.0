package neuro.expenses.register.ui.bills

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import neuro.expenses.register.ui.common.composables.datetime.DateTimeComposable
import neuro.expenses.register.ui.common.composables.edit.SaveDeleteComposable
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.ui.theme.grey_fog_lighter
import neuro.expenses.register.viewmodel.edit.bill.EditBillViewModel
import neuro.expenses.register.viewmodel.edit.bill.model.BillItemModel

@Composable
fun EditBillComposable(
  fragmentActivity: FragmentActivity,
  editBillViewModel: EditBillViewModel,
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
      text = editBillViewModel.placeName.value,
      fontWeight = FontWeight.Bold,
      fontSize = MaterialTheme.typography.h5.fontSize
    )
    BillItemHeaderComposable()
    LazyColumn(
      Modifier
        .background(color = grey_fog_lighter)
        .heightIn(max = 360.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(
        editBillViewModel.billItems.value,
        { billItemModel: BillItemModel -> billItemModel.id }) { billItemModel ->
        BillItemComposable(billItemModel)
      }
    }
    BillItemFooterComposable(editBillViewModel.total.value)
    DateTimeComposable(
      modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
      fragmentActivity = fragmentActivity,
      calendar = editBillViewModel.calendar
    )
    SaveDeleteComposable({}, {})
  }
}

@Preview
@Composable
fun PreviewEditBillComposable() {
  ExpensesRegisterTheme {
    val editBillViewModel = EditBillViewModel()
    editBillViewModel.placeName.value = "Bitoque"
    editBillViewModel.billItems.value = emptyList()

    EditBillComposable(FragmentActivity(), editBillViewModel)
  }
}