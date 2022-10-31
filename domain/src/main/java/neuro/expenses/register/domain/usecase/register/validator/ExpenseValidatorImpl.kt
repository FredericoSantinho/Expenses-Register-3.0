package neuro.expenses.register.domain.usecase.register.validator

import neuro.expenses.register.domain.entity.Expense
import neuro.expenses.register.domain.usecase.category.IsValidCategory

class ExpenseValidatorImpl(val isValidCategory: IsValidCategory) : ExpenseValidator {
  override fun validate(expense: Expense): List<RegisterExpenseError> {
    val errors = mutableListOf<RegisterExpenseError>()

    if (expense.description.isBlank()) {
      errors.add(RegisterExpenseError.EMPTY_DESCRIPTION)
    }
    if (!isValidCategory.isValidCategory(expense.category)) {
      errors.add(RegisterExpenseError.INVALID_CATEGORY)
    }
    if (expense.place.isBlank()) {
      errors.add(RegisterExpenseError.EMPTY_PLACE)
    }

    return errors
  }
}