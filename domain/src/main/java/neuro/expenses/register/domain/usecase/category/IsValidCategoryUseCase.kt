package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Single

interface IsValidCategoryUseCase {
  fun isValidCategory(category: String): Single<Boolean>
}