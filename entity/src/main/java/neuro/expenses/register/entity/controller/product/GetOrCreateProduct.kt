package neuro.expenses.register.entity.controller.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.Product

interface GetOrCreateProduct {
  fun getOrCreateProduct(description: String, variableAmount: Boolean): Single<Product>
}