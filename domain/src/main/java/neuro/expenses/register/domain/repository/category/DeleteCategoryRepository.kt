package neuro.expenses.register.domain.repository.category

import io.reactivex.rxjava3.core.Completable

interface DeleteCategoryRepository {
  fun deleteCategory(categoryId: Long): Completable
}