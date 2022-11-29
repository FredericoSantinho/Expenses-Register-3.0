package neuro.expenses.register.entity.expense.validator

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.model.Expense

interface ExpenseValidator {
  fun validate(expense: Expense): Completable
}