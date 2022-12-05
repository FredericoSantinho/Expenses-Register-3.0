package neuro.expenses.register.domain.repository.category

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.CategoryDto

interface CreateCategoryRepository {
  /**
   * Create a category.
   *
   * @param categoryDto Category to create.
   * @return Completable that completes in case of success or emits an error with a
   * CreateCategoryError in case there's already a category with the same name.
   */
  fun createCategory(categoryDto: CategoryDto): Completable
}
