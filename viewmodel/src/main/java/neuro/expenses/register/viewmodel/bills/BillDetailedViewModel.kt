package neuro.expenses.register.viewmodel.bills

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.viewmodel.common.formatter.CurrencyFormatter
import neuro.expenses.register.viewmodel.edit.bill.mapper.BillItemViewModelMapper
import neuro.expenses.register.viewmodel.edit.bill.model.BillItemViewModel
import java.util.*

class BillDetailedViewModel(
  private val billItemViewModelMapper: BillItemViewModelMapper,
  private val currencyFormatter: CurrencyFormatter
) {

  val placeName = mutableStateOf("")
  val calendar = mutableStateOf(Calendar.getInstance())
  val billItems = mutableStateOf(emptyList<BillItemViewModel>())
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
    billItems.value = billDto.billItems.map(billItemViewModelMapper::map)
    total.value = computeTotal()
  }

  private fun computeTotal(): String {
    return currencyFormatter.format(billItems.value.map { it.price() * it.amount() }.sum())
  }
}
