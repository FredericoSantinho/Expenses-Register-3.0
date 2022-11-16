package neuro.expenses.register.entity.controller.product

import io.reactivex.rxjava3.core.Single

interface GeneratePlaceProductId {
  fun newId(): Single<Long>
}