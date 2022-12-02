package neuro.expenses.register.domain.repository.category

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.usecase.category.DeleteCategoryError

interface DeleteCategoryRepository {
  /**
   * Delete a category.
   *
   * @throws DeleteCategoryError in case there's active relations with the category to be deleted.
   */
  fun deleteCategory(categoryId: Long): Completable
}