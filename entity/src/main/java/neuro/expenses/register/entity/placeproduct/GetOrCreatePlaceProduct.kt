package neuro.expenses.register.entity.placeproduct

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.model.PlaceProduct

interface GetOrCreatePlaceProduct {
  fun getOrCreatePlaceProduct(
    description: String,
    category: String,
    price: Double,
    variableAmount: Boolean,
    iconUrl: String
  ): Single<PlaceProduct>
}