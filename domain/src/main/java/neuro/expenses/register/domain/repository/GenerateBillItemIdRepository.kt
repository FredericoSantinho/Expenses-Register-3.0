package neuro.expenses.register.domain.repository

import io.reactivex.rxjava3.core.Single

interface GenerateBillItemIdRepository {
  fun newId(): Single<Long>
}