package neuro.expenses.register.domain.repository.product

import io.reactivex.rxjava3.core.Single

interface GeneratePlaceProductIdRepository {
  fun newId(): Single<Long>
}