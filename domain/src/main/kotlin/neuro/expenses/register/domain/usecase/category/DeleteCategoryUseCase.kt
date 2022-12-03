package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Completable

interface DeleteCategoryUseCase {
  /**
   * Delete a category.
   *
   * @throws DeleteCategoryError in case there's active relations with the category to be deleted.
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