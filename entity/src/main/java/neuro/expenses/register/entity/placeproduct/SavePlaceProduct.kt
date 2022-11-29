package neuro.expenses.register.entity.placeproduct

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.model.PlaceProduct

interface SavePlaceProduct {
  fun savePlaceProduct(placeProduct: PlaceProduct): Completable
}