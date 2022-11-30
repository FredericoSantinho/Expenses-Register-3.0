package neuro.expenses.register.viewmodel.bill.mapper

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.viewmodel.bill.BillCardViewModel

interface BillViewModelMapper {
  fun map(
    billDto: BillDto, editable: Boolean, opened: Boolean, onBillLongClick: (Long) -> Unit
  ): BillCardViewModel
}