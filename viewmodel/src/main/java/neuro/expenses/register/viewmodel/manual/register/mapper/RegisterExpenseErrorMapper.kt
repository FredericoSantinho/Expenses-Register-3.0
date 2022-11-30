package neuro.expenses.register.viewmodel.manual.register.mapper

import neuro.expenses.register.domain.usecase.expense.RegisterExpenseError
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiState.Message
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiState.UiStateError

fun RegisterExpenseError.toViewmodel(): UiStateError {
  return when (this) {
    RegisterExpenseError.EMPTY_DESCRIPTION -> UiStateError.ShowDescriptionError(Message.EMPTY_DESCRIPTION)
    RegisterExpenseError.INVALID_CATEGORY -> UiStateError.ShowCategoryError(Message.CATEGORY_DOES_NOT_EXIST)
    RegisterExpenseError.EMPTY_PLACE -> UiStateError.ShowPlaceError(Message.EMPTY_PLACE)
    RegisterExpenseError.INVALID_AMOUNT -> UiStateError.ShowAmountError(Message.INVALID_AMOUNT)
  }
}

fun List<RegisterExpenseError>.toViewmodel(): List<UiStateError> = map { it.toViewmodel() }