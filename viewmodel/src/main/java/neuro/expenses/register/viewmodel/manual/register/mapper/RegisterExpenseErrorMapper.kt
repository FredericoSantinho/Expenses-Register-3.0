package neuro.expenses.register.viewmodel.manual.register.mapper

import neuro.expenses.register.domain.usecase.register.validator.RegisterExpenseError
import neuro.expenses.register.ui.manual.register.UiStateError

interface RegisterExpenseErrorMapper {
  fun map(errors: List<RegisterExpenseError>): List<UiStateError>
}