package neuro.expenses.register.viewmodel.bill

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.viewmodel.bill.mapper.BillItemModelMapper
import neuro.expenses.register.viewmodel.bill.model.BillItemModel
import neuro.expenses.register.viewmodel.common.formatter.CurrencyFormatter
import java.util.*

class BillDetailedViewModel(
  private val billItemModelMapper: BillItemModelMapper,
  private val currencyFormatter: CurrencyFormatter
) {

  val placeName = mutableStateOf("")
  val calendar = mutableStateOf(Calendar.getInstance())
  val billItems = mutableStateOf(emptyList<BillItemModel>())
  val total = mutableStateOf("")

  fun onSaveButton() {
  }

  fun onDeleteButton() {
  }

  fun onBillItemsChange() {
    total.value = computeTotal()
  }

  fun setBillDetailedViewModel(billDto: BillDto) {
    placeName.value = billDto.place.name
    calendar.value = billDto.calendar
    billItems.value = billDto.billItems.map(billItemModelMapper::map)
    total.value = computeTotal()
  }

  private fun computeTotal(): String {
    return currencyFormatter.format(billItems.value.map { it.price() * it.amount() }.sum())
  }
}
