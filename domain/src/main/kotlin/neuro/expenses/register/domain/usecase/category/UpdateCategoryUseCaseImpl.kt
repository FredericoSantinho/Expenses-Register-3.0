package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.repository.category.UpdateCategoryRepository

class UpdateCategoryUseCaseImpl(private val updateCategoryRepository: UpdateCategoryRepository) :
  UpdateCategoryUseCase {
  override fun updateCategory(categoryDto: CategoryDto): Completable {
    return updateCategoryRepository.updateCategory(categoryDto)
  }
}