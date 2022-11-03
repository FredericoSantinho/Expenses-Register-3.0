package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Observable

interface ObserveCategoriesUseCase {
  /**
   * @return an Observable that will emit a list with all categories each time categories change.
   */
  fun observeCategories(): Observable<List<String>>
}