package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.CategoryDto

interface SaveCategoryUseCase {
  fun saveCategory(categoryDto: CategoryDto): Completable
  fun saveCategories(categoriesDtos: List<CategoryDto>): Completable {
    return Observable.fromIterable(categoriesDtos).flatMapCompletable { saveCategory(it) }
  }
}