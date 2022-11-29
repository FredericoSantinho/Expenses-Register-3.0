package neuro.expenses.register.entity.expense.validator

/**
 * Expense validation Exception.
 *
 * @param errors list of validation errors found.
 */
data class RegisterExpenseException(val errors: Set<RegisterExpenseError>) :
  IllegalArgumentException()