package neuro.expenses.register.domain.repository

import io.reactivex.rxjava3.core.Maybe

interface GetCategoryRepository {
  fun getCategory(name: String): Maybe<String>
}