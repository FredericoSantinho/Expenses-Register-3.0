package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.GetCategoryRepository

class IsValidCategoryUseCaseImpl(private val getCategoryRepository: GetCategoryRepository) :
  IsValidCategoryUseCase {
  override fun isValidCategory(category: String): Single<Boolean> {
    return getCategoryRepository.getCategory(category.lowercase()).map { true }
      .defaultIfEmpty(false)
  }
}