package neuro.expenses.register.viewmodel.bills

import neuro.expenses.register.domain.dto.BillDto

interface EditBillViewModelController {
  fun setEditBillViewModel(billDto: BillDto)
}