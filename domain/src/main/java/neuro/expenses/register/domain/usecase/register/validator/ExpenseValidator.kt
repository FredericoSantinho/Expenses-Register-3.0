package neuro.expenses.register.domain.usecase.register.validator

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.entity.Expense

interface ExpenseValidator {
  fun validate(expense: Expense): Single<List<RegisterExpenseError>>
}