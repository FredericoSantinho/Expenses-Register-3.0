package neuro.expenses.register.domain.repository.category

import io.reactivex.rxjava3.core.Single

interface GenerateCategoryIdRepository {
  fun newId(): Single<Long>
}