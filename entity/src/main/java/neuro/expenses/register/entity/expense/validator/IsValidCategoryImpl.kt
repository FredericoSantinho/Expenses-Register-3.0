package neuro.expenses.register.entity.expense.validator

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.category.GetCategory

class IsValidCategoryImpl(private val getCategory: GetCategory) : IsValidCategory {
  override fun isValidCategory(category: String): Single<Boolean> {
    return getCategory.getCategory(category.lowercase()).map { true }
      .defaultIfEmpty(false)
  }
}