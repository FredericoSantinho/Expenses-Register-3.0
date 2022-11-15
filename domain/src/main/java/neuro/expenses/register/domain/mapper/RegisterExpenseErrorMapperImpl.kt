package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.usecase.expense.RegisterExpenseError
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseError.*

class RegisterExpenseErrorMapperImpl : RegisterExpenseErrorMapper {
  override fun map(registerExpenseError: neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseError): neuro.expenses.register.domain.usecase.expense.RegisterExpenseError {
    return when (registerExpenseError) {
      neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseError.EMPTY_DESCRIPTION -> EMPTY_DESCRIPTION
      neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseError.INVALID_CATEGORY -> INVALID_CATEGORY
      neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseError.EMPTY_PLACE -> EMPTY_PLACE
      neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseError.INVALID_AMOUNT -> INVALID_AMOUNT
    }
  }

  override fun map(registerExpenseErrorList: List<neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseError>): List<RegisterExpenseError> {
    return registerExpenseErrorList.map { map(it) }
  }
}