package neuro.expenses.register.viewmodel.bill.mapper

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.viewmodel.bill.BillViewModel

interface BillViewModelMapper {
  fun map(billDto: BillDto, editable: Boolean): BillViewModel
}