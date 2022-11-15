package neuro.expenses.register.entity.controller.expense

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.Expense

interface RegisterExpense {
  /**
   * Register expense.
   * @return a Completable that completes if register succeeds or emits an error in case validation fails.
   * @throws neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseException with a list of validation errors.
   */
  fun registerExpense(expense: Expense): Completable
}