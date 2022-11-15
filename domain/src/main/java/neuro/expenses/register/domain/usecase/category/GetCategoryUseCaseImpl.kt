package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.repository.GetCategoryRepository

class GetCategoryUseCaseImpl(private val getCategoryRepository: GetCategoryRepository) :
  GetCategoryUseCase {
  override fun getCategory(name: String): Maybe<CategoryDto> {
    return getCategoryRepository.getCategory(name.lowercase())
  }
}