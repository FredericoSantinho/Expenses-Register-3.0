package neuro.expenses.register.domain.repository

import io.reactivex.rxjava3.core.Observable

interface GetCategoriesRepository {
  fun getCategories(): Observable<List<String>>
}