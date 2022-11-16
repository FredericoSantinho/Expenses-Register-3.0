package neuro.expenses.register.entity.controller.expense

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.Expense
import neuro.expenses.register.entity.controller.bill.BillController
import neuro.expenses.register.entity.controller.bill.GetLastBill
import neuro.expenses.register.entity.controller.bill.SaveBill
import neuro.expenses.register.entity.controller.expense.validator.ExpenseValidator
import neuro.expenses.register.entity.controller.place.GeneratePlaceId
import neuro.expenses.register.entity.controller.place.GetOrCreatePlace

class RegisterExpenseImpl(
  private val billController: BillController,
  private val getLastBill: GetLastBill,
  private val saveBill: SaveBill,
  private val getOrCreatePlace: GetOrCreatePlace,
  private val generatePlaceId: GeneratePlaceId,
  private val expenseValidator: ExpenseValidator
) : RegisterExpense {
  override fun registerExpense(expense: Expense): Completable {
    return expenseValidator.validate(expense).andThen(
      billController.add(expense)
    ).ignoreElement()
  }
}