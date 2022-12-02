package neuro.expenses.register.entity.expense.validator

/**
 * Expense validation error.
 */
enum class RegisterExpenseError {
  EMPTY_DESCRIPTION,
  INVALID_CATEGORY,
  EMPTY_PLACE,
  INVALID_AMOUNT
}
