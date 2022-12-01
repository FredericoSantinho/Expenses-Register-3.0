package neuro.expenses.register.domain.usecase.expense

enum class RegisterExpenseError {
  EMPTY_DESCRIPTION,
  CATEGORY_NOT_EXISTS,
  EMPTY_PLACE,
  INVALID_AMOUNT
}
