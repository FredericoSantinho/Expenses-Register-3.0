package neuro.expenses.register.entity.controller

import io.reactivex.rxjava3.core.Single

interface GenerateProductId {
  fun newId(): Single<Long>
}