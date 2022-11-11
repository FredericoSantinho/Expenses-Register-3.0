package neuro.expenses.register.domain.usecase.bill

import neuro.expenses.register.entity.Bill

interface SaveBillUseCase {
  fun save(bill: Bill)
}