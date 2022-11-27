package neuro.expenses.register.domain.repository.category

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.CategoryDto

interface CreateCategoryRepository {
  /**
   * Create a category.
   *
   * @throws CreateCategoryError in case there's already a category with the same name.
   */
  fun createCategory(categoryDto: CategoryDto): Completable
}

class CreateCategoryError : java.lang.IllegalArgumentException()