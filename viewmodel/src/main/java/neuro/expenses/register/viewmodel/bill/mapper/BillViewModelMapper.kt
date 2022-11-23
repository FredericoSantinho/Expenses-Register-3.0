package neuro.expenses.register.viewmodel.bill.mapper

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.bill.model.BillModel

interface BillViewModelMapper {
  fun map(
    billDto: BillDto, editable: Boolean, opened: Boolean, onLongClick: (BillModel) -> Unit
  ): BillViewModel
}