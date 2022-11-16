package neuro.expenses.register.domain.repository

import io.reactivex.rxjava3.core.Single

interface GeneratePlaceIdRepository {
  fun newId(): Single<Long>
}