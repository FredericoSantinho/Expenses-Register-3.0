package neuro.expenses.register.presentation.mocks.bill

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.bill.model.BillItemModel
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatter
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatterImpl

fun billItemModelMock(): BillItemModel {
  val decimalFormatter: DecimalFormatter = DecimalFormatterImpl(2)

  val id = 0L
  val description = "Sagres Média 0,33cl"
  val price = mutableStateOf("1.30")
  val amount = mutableStateOf("2.00")
  val total = mutableStateOf("2.60")
  val iconUrl = ""
  val billItemModel = BillItemModel(
    id, description, price, amount, total, iconUrl, decimalFormatter
  )
  return billItemModel
}
