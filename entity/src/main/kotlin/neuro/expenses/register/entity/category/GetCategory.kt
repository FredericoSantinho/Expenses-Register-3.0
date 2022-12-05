package neuro.expenses.register.entity.category

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.model.Category

interface GetCategory {
  /**
   * Get a Category.
   *
   * @param nameLowercase Category name in lowercase.
   * @return Maybe with the Category, or empty if no Category with the given name exists.
   */
  fun getCategory(nameLowercase: String): Maybe<Category>
}