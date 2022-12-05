package neuro.expenses.register.domain.repository.category

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.CategoryDto

interface UpdateCategoryRepository {
  /**
   * Update a Category.
   *
   * @param categoryDto Category to update.
   * @return Completable that completes in case of success or emits an error with a
   * UpdateCategoryError in case there's already a category with the same name.
   */
  fun updateCategory(categoryDto: CategoryDto): Completable
}
