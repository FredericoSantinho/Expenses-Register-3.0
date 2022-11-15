package neuro.expenses.register.entity.controller.expense.validator

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.Expense

interface ExpenseValidator {
  fun validate(expense: Expense): Completable
}