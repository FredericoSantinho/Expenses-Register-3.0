package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.repository.category.DeleteCategoryRepository

class DeleteCategoryUseCaseImpl(private val deleteCategoryRepository: DeleteCategoryRepository) :
  DeleteCategoryUseCase {
  override fun deleteCategory(categoryId: Long): Completable {
    return deleteCategoryRepository.deleteCategory(categoryId)
  }
}