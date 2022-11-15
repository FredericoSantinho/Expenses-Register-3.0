package neuro.expenses.register.domain.usecase.expense

enum class RegisterExpenseError {
  EMPTY_DESCRIPTION,
  INVALID_CATEGORY,
  EMPTY_PLACE,
  INVALID_AMOUNT
}
