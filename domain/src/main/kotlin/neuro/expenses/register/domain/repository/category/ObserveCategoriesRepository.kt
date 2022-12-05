package neuro.expenses.register.domain.repository.category

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.CategoryDto

interface ObserveCategoriesRepository {
  /**
   * Observe all categories.
   *
   * @return an Observable that will emit a list with all categories each time categories change.
   */
  fun observeCategories(): Observable<List<CategoryDto>>
}