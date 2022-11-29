package neuro.expenses.register.entity.product

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.model.Product

interface GetProduct {
  fun getProduct(description: String): Maybe<Product>
}