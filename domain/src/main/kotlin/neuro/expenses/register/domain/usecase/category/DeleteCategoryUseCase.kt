package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Completable

interface DeleteCategoryUseCase {
  /**
   * Delete a category.
   *
   * @param categoryId Category id.
   * @return Completable that completes in case of success or emits an error with a
   * DeleteCategoryError in case there's active relations with the category to be deleted.
   */
  fun deleteCategory(categoryId: Long): Completable
}

class DeleteCategoryError : java.lang.IllegalArgumentException() {
  override fun equals(other: Any?): Boolean {
    return other is DeleteCategoryError
  }

  override fun hashCode(): Int {
    return DeleteCategoryError::class.toString().hashCode()
  }
}