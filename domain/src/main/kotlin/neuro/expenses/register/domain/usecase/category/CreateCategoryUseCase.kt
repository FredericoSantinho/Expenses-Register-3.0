package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Completable

interface CreateCategoryUseCase {
  /**
   * Create a category.
   *
   * @param name Category name.
   * @param iconUrl Category iconUrl.
   * @return Completable that completes in case of success or emits an error with a
   * CreateCategoryError in case there's already a category with the same name.
   */
  fun createCategory(name: String, iconUrl: String): Completable
}

class CreateCategoryError() : java.lang.IllegalArgumentException() {
  override fun equals(other: Any?): Boolean {
    return other is CreateCategoryError
  }

  override fun hashCode(): Int {
    return CreateCategoryError::class.toString().hashCode()
  }
}