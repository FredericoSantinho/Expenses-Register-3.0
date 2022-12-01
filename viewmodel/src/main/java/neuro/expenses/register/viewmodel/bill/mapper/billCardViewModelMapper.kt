package neuro.expenses.register.viewmodel.bill.mapper

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.viewmodel.bill.IBillCardViewModel

interface billCardViewModelMapper {
  fun map(
    billDto: BillDto, editable: Boolean, opened: Boolean, onBillLongClick: (Long) -> Unit
  ): IBillCardViewModel
}