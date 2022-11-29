package neuro.expenses.register.entity.expense.validator

data class RegisterExpenseException(val errors: Set<RegisterExpenseError>) :
  IllegalArgumentException()