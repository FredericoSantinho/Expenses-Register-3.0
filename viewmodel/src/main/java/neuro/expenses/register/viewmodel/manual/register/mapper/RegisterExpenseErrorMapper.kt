package neuro.expenses.register.viewmodel.manual.register.mapper

import neuro.expenses.register.domain.usecase.expense.RegisterExpenseError
import neuro.expenses.register.viewmodel.manual.register.Message
import neuro.expenses.register.viewmodel.manual.register.UiStateError

fun RegisterExpenseError.toPresentation(): UiStateError {
  return when (this) {
    RegisterExpenseError.EMPTY_DESCRIPTION -> UiStateError.ShowDescriptionError(Message.EMPTY_DESCRIPTION)
    RegisterExpenseError.INVALID_CATEGORY -> UiStateError.ShowCategoryError
    RegisterExpenseError.EMPTY_PLACE -> UiStateError.ShowPlaceError(Message.EMPTY_PLACE)
    RegisterExpenseError.INVALID_AMOUNT -> UiStateError.ShowAmountError(Message.INVALID_AMOUNT)
  }
}

fun List<RegisterExpenseError>.toPresentation(): List<UiStateError> = map { it.toPresentation() }