package neuro.expenses.register.domain.usecase.register.validator

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.Expense

interface ExpenseValidator {
  fun validate(expense: Expense): Completable
}