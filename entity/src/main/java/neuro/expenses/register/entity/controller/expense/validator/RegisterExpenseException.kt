package neuro.expenses.register.entity.controller.expense.validator

data class RegisterExpenseException(val errors: Set<RegisterExpenseError>) :
  IllegalArgumentException()