package neuro.expenses.register.entity.product

import io.reactivex.rxjava3.core.Single

interface GenerateProductId {
  fun newId(): Single<Long>
}