package neuro.expenses.register.entity.bill

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.model.Bill
import neuro.expenses.register.entity.model.Expense

interface BillController {
  /**
   * Add an expense to a bill.
   *
   * If there's already an open bill and the place is the same as the expense's one, the Expense
   * will be added to this bill. Otherwise a new bill will be created with the expense's place and
   * calendar to which the Expense will be added.
   *
   * @param expense Expense
   * @return Single with the updated Bill.
   */
  fun add(expense: Expense): Single<Bill>
}