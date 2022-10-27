package neuro.expenses.register.domain.usecase.register

import neuro.expenses.register.domain.dto.BillItemDto
import java.util.*

interface RegisterExpenseUseCase {
  /**
   * Register expense.
   * @return if register succeeds, an empty list. Otherwise, a list of errors.
   */
  fun registerExpense(billItemDto: BillItemDto, calendar: Calendar): List<RegisterExpenseError>
}