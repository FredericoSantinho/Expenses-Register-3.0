package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.repository.SaveCategoryRepository

class SaveCategoryUseCaseImpl(private val saveCategoryRepository: SaveCategoryRepository) :
  SaveCategoryUseCase {
  override fun saveCategory(categoryDto: CategoryDto): Completable {
    return saveCategoryRepository.saveCategory(categoryDto)
  }
}