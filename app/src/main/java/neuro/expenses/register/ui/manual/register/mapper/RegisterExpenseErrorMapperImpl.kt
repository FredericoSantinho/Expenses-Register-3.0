package neuro.expenses.register.ui.manual.register.mapper

import neuro.expenses.register.domain.usecase.register.validator.RegisterExpenseError
import neuro.expenses.register.domain.usecase.register.validator.RegisterExpenseError.*
import neuro.expenses.register.ui.manual.register.Message
import neuro.expenses.register.ui.manual.register.UiStateError

class RegisterExpenseErrorMapperImpl : RegisterExpenseErrorMapper {
  override fun map(errors: List<RegisterExpenseError>): List<UiStateError> {
    val list = mutableListOf<UiStateError>()
    errors.forEach {
      val error: UiStateError
      when (it) {
        EMPTY_DESCRIPTION -> {
          error = UiStateError.ShowDescriptionError(Message.EMPTY_DESCRIPTION)
        }
        INVALID_CATEGORY -> error = UiStateError.ShowCategoryError
        EMPTY_PLACE -> error = UiStateError.ShowPlaceError(Message.EMPTY_PLACE)
        INVALID_AMOUNT -> error = UiStateError.ShowAmountError(Message.INVALID_AMOUNT)
      }
      list.add(error)
    }
    return list
  }
}