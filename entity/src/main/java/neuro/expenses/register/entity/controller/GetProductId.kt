package neuro.expenses.register.entity.controller

import io.reactivex.rxjava3.core.Single

interface GetProductId {
  fun getProductId(description: String, category: String, price: Double): Single<Long>
}