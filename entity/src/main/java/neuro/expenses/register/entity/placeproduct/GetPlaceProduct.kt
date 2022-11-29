package neuro.expenses.register.entity.placeproduct

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.model.PlaceProduct

interface GetPlaceProduct {
  fun getPlaceProduct(
    description: String,
    category: String,
    price: Double
  ): Maybe<PlaceProduct>
}