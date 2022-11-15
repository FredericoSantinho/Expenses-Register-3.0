package neuro.expenses.register.viewmodel.manual.register.mapper

import neuro.expenses.register.domain.usecase.expense.RegisterExpenseError
import neuro.expenses.register.viewmodel.manual.register.Message
import neuro.expenses.register.viewmodel.manual.register.UiStateError

class RegisterExpenseErrorMapperImpl : RegisterExpenseErrorMapper {
  override fun map(errors: List<RegisterExpenseError>): List<UiStateError> {
    val list = mutableListOf<UiStateError>()
    errors.forEach {
      val error: UiStateError
      when (it) {
        RegisterExpenseError.EMPTY_DESCRIPTION -> {
          error = UiStateError.ShowDescriptionError(Message.EMPTY_DESCRIPTION)
        }
        RegisterExpenseError.INVALID_CATEGORY -> error = UiStateError.ShowCategoryError
        RegisterExpenseError.EMPTY_PLACE -> error = UiStateError.ShowPlaceError(Message.EMPTY_PLACE)
        RegisterExpenseError.INVALID_AMOUNT -> error =
          UiStateError.ShowAmountError(Message.INVALID_AMOUNT)
      }
      list.add(error)
    }
    return list
  }
}