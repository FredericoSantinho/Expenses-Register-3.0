package neuro.expenses.register.entity.controller.bill

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.Bill
import neuro.expenses.register.entity.Expense

interface BillController {
  fun add(expense: Expense): Single<Bill>
}