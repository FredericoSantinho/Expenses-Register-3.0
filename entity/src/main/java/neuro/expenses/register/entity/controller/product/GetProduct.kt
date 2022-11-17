package neuro.expenses.register.entity.controller.product

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.Product

interface GetProduct {
  fun getProduct(description: String): Maybe<Product>
}