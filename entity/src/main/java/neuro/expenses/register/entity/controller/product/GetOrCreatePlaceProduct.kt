package neuro.expenses.register.entity.controller.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.PlaceProduct

interface GetOrCreatePlaceProduct {
  fun getOrCreateProduct(
    description: String,
    category: String,
    price: Double,
    variableAmount: Boolean,
    place: String
  ): Single<PlaceProduct>
}