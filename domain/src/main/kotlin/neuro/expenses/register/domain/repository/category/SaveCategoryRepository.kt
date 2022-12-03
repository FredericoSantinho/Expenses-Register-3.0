package neuro.expenses.register.domain.repository.category

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.CategoryDto

interface SaveCategoryRepository {
  fun saveCategory(categoryDto: CategoryDto): Completable
}

class SaveCategoryError : java.lang.IllegalArgumentException() {
  override fun equals(other: Any?): Boolean {
    return other is SaveCategoryError
  }

  override fun hashCode(): Int {
    return SaveCategoryError::class.toString().hashCode()
  }
}