package neuro.expenses.register.domain.repository.category

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.CategoryDto

interface UpdateCategoryRepository {
  /**
   * Update a category.
   *
   * @throws UpdateCategoryError in case there's already a category with the same name.
   */
  fun updateCategory(categoryDto: CategoryDto): Completable
}

class UpdateCategoryError : java.lang.IllegalArgumentException()