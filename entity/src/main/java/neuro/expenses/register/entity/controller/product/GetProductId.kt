package neuro.expenses.register.entity.controller.product

import io.reactivex.rxjava3.core.Maybe

interface GetProductId {
  fun getProductId(description: String, category: String, price: Double): Maybe<Long>
}