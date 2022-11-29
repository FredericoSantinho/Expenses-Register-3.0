package neuro.expenses.register.entity.expense

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.model.Expense

interface RegisterExpense {
  /**
   * Register expense.
   *
   * @return Completable that completes if register succeeds or emits an error in case validation
   * fails, with an RegisterExpenseException containing a list of validation errors.
   */
  fun registerExpense(expense: Expense): Completable
}