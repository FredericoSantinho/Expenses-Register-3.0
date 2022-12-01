package neuro.expenses.register.viewmodel.manual.register.mapper

import neuro.expenses.register.domain.usecase.expense.RegisterExpenseError
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiState.UiStateError

fun RegisterExpenseError.toViewmodel(): UiStateError {
  return when (this) {
    RegisterExpenseError.EMPTY_DESCRIPTION -> UiStateError.ShowEmptyDescriptionError()
    RegisterExpenseError.CATEGORY_NOT_EXISTS -> UiStateError.ShowCategoryNotExistsError()
    RegisterExpenseError.EMPTY_PLACE -> UiStateError.ShowEmptyPlaceError()
    RegisterExpenseError.INVALID_AMOUNT -> UiStateError.ShowInvalidAmountError()
  }
}

fun List<RegisterExpenseError>.toViewmodel(): List<UiStateError> = map { it.toViewmodel() }