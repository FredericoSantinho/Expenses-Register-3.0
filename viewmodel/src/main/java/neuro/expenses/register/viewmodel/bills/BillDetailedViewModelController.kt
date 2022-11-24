package neuro.expenses.register.viewmodel.bills

import neuro.expenses.register.domain.dto.BillDto

interface BillDetailedViewModelController {
  fun setEditBillViewModel(billDto: BillDto)
}