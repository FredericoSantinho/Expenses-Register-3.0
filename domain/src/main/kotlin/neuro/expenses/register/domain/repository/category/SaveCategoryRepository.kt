package neuro.expenses.register.domain.repository.category

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.CategoryDto

interface SaveCategoryRepository {
  /**
   * Save a Category.
   *
   * @param categoryDto Category to save.
   * @return Completable that completes in case of success or emits an error with a
   * SaveCategoryError in case there's already a category with the same name.
   */
  fun saveCategory(categoryDto: CategoryDto): Completable
}