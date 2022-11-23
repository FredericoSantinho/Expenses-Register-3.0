package neuro.expenses.register.entity.controller.place

import io.reactivex.rxjava3.core.Completable

interface AddPlaceProduct {
  fun addPlaceProduct(placeId: Long, placeProductId: Long): Completable
}