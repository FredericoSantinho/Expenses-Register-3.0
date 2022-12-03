package neuro.expenses.register.entity.expense.validator

/**
 * Expense validation error.
 */
enum class RegisterExpenseError {
  EMPTY_DESCRIPTION,
  CATEGORY_NOT_EXISTS,
  EMPTY_PLACE,
  INVALID_AMOUNT
}
