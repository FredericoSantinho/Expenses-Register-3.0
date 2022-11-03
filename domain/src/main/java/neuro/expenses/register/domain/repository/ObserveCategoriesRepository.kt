package neuro.expenses.register.domain.repository

import io.reactivex.rxjava3.core.Observable

interface ObserveCategoriesRepository {
  fun observeCategories(): Observable<List<String>>
}