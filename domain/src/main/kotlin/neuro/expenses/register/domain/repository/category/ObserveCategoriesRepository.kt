package neuro.expenses.register.domain.repository.category

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.CategoryDto

interface ObserveCategoriesRepository {
  fun observeCategories(): Observable<List<CategoryDto>>
}