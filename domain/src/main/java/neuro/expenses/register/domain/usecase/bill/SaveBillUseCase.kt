package neuro.expenses.register.domain.usecase.bill

import neuro.expenses.register.domain.entity.Bill

interface SaveBillUseCase {
  fun save(bill: Bill, place: String)
}