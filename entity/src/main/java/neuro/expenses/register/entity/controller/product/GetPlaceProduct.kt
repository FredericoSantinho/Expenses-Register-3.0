package neuro.expenses.register.entity.controller.product

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.PlaceProduct

interface GetPlaceProduct {
  fun getPlaceProduct(
    description: String,
    category: String,
    price: Double
  ): Maybe<PlaceProduct>
}