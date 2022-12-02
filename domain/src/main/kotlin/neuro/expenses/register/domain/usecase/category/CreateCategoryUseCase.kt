package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Completable

interface CreateCategoryUseCase {
  fun createCategory(name: String, iconUrl: String): Completable
}