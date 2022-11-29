package neuro.expenses.register.entity.expense.validator

import io.reactivex.rxjava3.core.Single

interface IsValidCategory {
  /**
   * Checks if a Category is valid (ie, exists) based on its name.
   *
   * @param category Category name.
   * @return Single with true if category is valid, or false otherwise.
   */
  fun isValidCategory(category: String): Single<Boolean>
}