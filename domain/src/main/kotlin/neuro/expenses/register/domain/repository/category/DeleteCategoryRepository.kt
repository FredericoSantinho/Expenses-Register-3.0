package neuro.expenses.register.domain.repository.category

import io.reactivex.rxjava3.core.Completable

interface DeleteCategoryRepository {
  /**
   * Delete a category.
   *
   * @param categoryId Category id.
   * @return Completable that completes in case of success or emits an error with a
   * DeleteCategoryError in case there's active relations with the category to be deleted.
   */
  fun deleteCategory(categoryId: Long): Completable
}