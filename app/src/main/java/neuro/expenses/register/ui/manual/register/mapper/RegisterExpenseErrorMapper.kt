package neuro.expenses.register.ui.manual.register.mapper

import neuro.expenses.register.domain.usecase.register.RegisterExpenseError
import neuro.expenses.register.ui.manual.register.UiStateError

interface RegisterExpenseErrorMapper {
  fun map(errors: List<RegisterExpenseError>): List<UiStateError>
}