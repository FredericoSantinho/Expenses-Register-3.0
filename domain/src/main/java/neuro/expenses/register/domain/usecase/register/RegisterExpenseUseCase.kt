package neuro.expenses.register.domain.usecase.register

import neuro.expenses.register.domain.entity.BillItem
import java.util.*

interface RegisterExpenseUseCase {
  /**
   * Register expense.
   * @return if register succeeds, an empty list. Otherwise, a list of errors.
   */
  fun registerExpense(billItem: BillItem, calendar: Calendar): List<RegisterExpenseError>
}