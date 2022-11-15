package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.usecase.expense.RegisterExpenseError

interface RegisterExpenseErrorMapper {
  fun map(registerExpenseError: neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseError): RegisterExpenseError
  fun map(registerExpenseErrorList: List<neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseError>): List<RegisterExpenseError>
}