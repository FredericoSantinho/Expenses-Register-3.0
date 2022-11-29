package neuro.expenses.register.entity.expense.validator

import io.reactivex.rxjava3.core.Single

interface IsValidCategory {
  fun isValidCategory(category: String): Single<Boolean>
}