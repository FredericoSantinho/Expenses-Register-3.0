package neuro.expenses.register.entity.controller.expense

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.Bill
import neuro.expenses.register.entity.Expense
import neuro.expenses.register.entity.controller.bill.BillController
import neuro.expenses.register.entity.controller.bill.GetLastBill
import neuro.expenses.register.entity.controller.bill.SaveBill
import neuro.expenses.register.entity.controller.expense.validator.ExpenseValidator
import java.util.*

class RegisterExpenseImpl(
  private val billController: BillController,
  private val getLastBill: GetLastBill,
  private val saveBill: SaveBill,
  private val expenseValidator: ExpenseValidator
) : RegisterExpense {
  private val defaultBillDto = Bill(0, "N/A", Calendar.getInstance(), 0.0, isOpen = false)

  override fun registerExpense(expense: Expense): Completable {
    return getLastBill.getLastBill().defaultIfEmpty(defaultBillDto)
      .flatMapCompletable { lastStoredBill ->
        val place = expense.place
        val calendar = expense.calendar
        val lastBill = if (!lastStoredBill.isOpen || lastStoredBill.place != place) {
          Bill(0, place, calendar)
        } else {
          lastStoredBill
        }

        return@flatMapCompletable expenseValidator.validate(expense).andThen(Completable.defer {
          return@defer billController.add(lastBill, expense).doOnSuccess { bill ->
            saveBill.save(bill)
          }.ignoreElement()
        })
      }
  }
}