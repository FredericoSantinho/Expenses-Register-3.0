package neuro.expenses.register.domain.usecase.register.validator

data class RegisterExpenseException(val errors: List<RegisterExpenseError>) :
  IllegalArgumentException()