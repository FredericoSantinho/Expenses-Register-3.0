package neuro.expenses.register.viewmodel.bills

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.viewmodel.common.formatter.CurrencyFormatter
import neuro.expenses.register.viewmodel.edit.bill.BillDetailedViewModel
import neuro.expenses.register.viewmodel.edit.bill.mapper.BillItemModelMapper

class BillDetailedViewModelControllerImpl(
  private val billDetailedViewModel: BillDetailedViewModel,
  private val billItemModelMapper: BillItemModelMapper,
  private val currencyFormatter: CurrencyFormatter
) : BillDetailedViewModelController {
  override fun setBillDetailedViewModel(billDto: BillDto) {
    billDetailedViewModel.placeName.value = billDto.place.name
    billDetailedViewModel.calendar.value = billDto.calendar
    billDetailedViewModel.billItems.value = billDto.billItems.map(billItemModelMapper::map)
    billDetailedViewModel.total.value = currencyFormatter.format(billDto.total)
  }
}