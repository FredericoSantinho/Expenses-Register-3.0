package neuro.expenses.register.entity.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.model.Product

interface GetOrCreateProduct {
  fun getOrCreateProduct(
    description: String,
    variableAmount: Boolean,
    iconUrl: String
  ): Single<Product>
}