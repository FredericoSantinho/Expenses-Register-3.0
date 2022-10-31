package neuro.expenses.register.domain.usecase.register

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.usecase.register.validator.RegisterExpenseError

interface RegisterExpenseUseCase {
  /**
   * Register expense.
   * @return if register succeeds, an empty list. Otherwise, a list of errors.
   */
  fun registerExpense(expenseDto: ExpenseDto): Single<List<RegisterExpenseError>>
}