package neuro.expenses.register.entity.bill

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.model.Bill
import neuro.expenses.register.entity.model.Expense

interface BillController {
  fun add(expense: Expense): Single<Bill>
}