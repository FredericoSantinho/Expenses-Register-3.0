package neuro.expenses.register.domain.usecase.register

import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.domain.usecase.register.validator.RegisterExpenseError

interface RegisterExpenseUseCase {
  /**
   * Register expense.
   * @return if register succeeds, an empty list. Otherwise, a list of errors.
   */
  fun registerExpense(billItemDto: BillItemDto): List<RegisterExpenseError>
}