package neuro.expenses.register.entity.controller.placeproduct

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.PlaceProduct

interface SavePlaceProduct {
  fun savePlaceProduct(placeProduct: PlaceProduct): Completable
}