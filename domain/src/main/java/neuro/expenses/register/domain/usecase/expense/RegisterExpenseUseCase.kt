package neuro.expenses.register.domain.usecase.expense

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.ExpenseDto

interface RegisterExpenseUseCase {
  /**
   * Register expense.
   * @return Completable that completes if register succeeds or emits an error in case validation
   * fails, with an RegisterExpenseException containing a list of validation errors.
   */
  fun registerExpense(expenseDto: ExpenseDto): Completable
}