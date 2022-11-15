package neuro.expenses.register.viewmodel.manual.register.mapper

import neuro.expenses.register.domain.usecase.expense.RegisterExpenseError
import neuro.expenses.register.viewmodel.manual.register.UiStateError

interface RegisterExpenseErrorMapper {
  fun map(errors: List<RegisterExpenseError>): List<UiStateError>
}