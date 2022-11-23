package neuro.expenses.register.viewmodel.bills

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatter
import neuro.expenses.register.viewmodel.edit.bill.EditBillViewModel
import neuro.expenses.register.viewmodel.edit.bill.mapper.BillItemModelMapper

class EditBillViewModelControllerImpl(
  private val editBillViewModel: EditBillViewModel,
  private val billItemModelMapper: BillItemModelMapper,
  private val decimalFormatter: DecimalFormatter,
  private val currency: String
) : EditBillViewModelController {
  override fun setEditBillViewModel(billDto: BillDto) {
    editBillViewModel.placeName.value = billDto.place.name
    editBillViewModel.calendar.value = billDto.calendar
    editBillViewModel.billItems.value = billDto.billItems.map(billItemModelMapper::map)
    editBillViewModel.total.value = decimalFormatter.format(billDto.total) + " $currency"
  }
}