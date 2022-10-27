package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Observable

interface GetCategoriesUseCase {
  /**
   * @return an Observable that will emit a list with all categories each time categories change.
   */
  fun getCategories(): Observable<List<String>>
}