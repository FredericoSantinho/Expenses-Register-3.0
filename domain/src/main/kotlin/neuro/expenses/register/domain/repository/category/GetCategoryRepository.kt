package neuro.expenses.register.domain.repository.category

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.CategoryDto

interface GetCategoryRepository {
  /**
   * Get a Category.
   *
   * @param nameLowercase Category name in lowercase.
   * @return Maybe with the Category, or empty if no Category with the given name exists.
   */
  fun getCategory(nameLowercase: String): Maybe<CategoryDto>
}