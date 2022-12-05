package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.CategoryDto

interface SaveCategoryUseCase {
  /**
   * Save a Category.
   *
   * @param categoryDto Category to save.
   * @return Completable that completes in case of success or emits an error with a
   * SaveCategoryError in case there's already a category with the same name.
   */
  fun saveCategory(categoryDto: CategoryDto): Completable

  /**
   * Save a list of categories.
   *
   * @param categoriesDtos list of categories to save.
   * @return Completable that completes in case of success or emits an error with a
   * SaveCategoryError in case there's already a category with the same name.
   */
  fun saveCategories(categoriesDtos: List<CategoryDto>): Completable {
    return Observable.fromIterable(categoriesDtos).flatMapCompletable { saveCategory(it) }
  }
}

class SaveCategoryError : java.lang.IllegalArgumentException() {
  override fun equals(other: Any?): Boolean {
    return other is SaveCategoryError
  }

  override fun hashCode(): Int {
    return SaveCategoryError::class.toString().hashCode()
  }
}