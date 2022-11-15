package neuro.expenses.register.domain.usecase.expense


class RegisterExpenseException(val errors: List<RegisterExpenseError>) :
  java.lang.IllegalArgumentException()