package neuro.expenses.register.entity.expense

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.model.Expense

interface RegisterExpense {
  /**
   * Register an Expense.
   *
   * @param expenseDto Expense to register.
   * @return Completable that completes if register succeeds or emits an error with an
   * RegisterExpenseException containing a list of validation errors in case validation fails.
   */
  fun registerExpense(expense: Expense): Completable
}