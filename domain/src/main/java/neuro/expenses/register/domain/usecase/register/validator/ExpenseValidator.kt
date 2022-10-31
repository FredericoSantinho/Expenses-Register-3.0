package neuro.expenses.register.domain.usecase.register.validator

import neuro.expenses.register.domain.entity.Expense

interface ExpenseValidator {
  fun validate(expense: Expense): List<RegisterExpenseError>
}