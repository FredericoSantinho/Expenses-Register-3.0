package neuro.expenses.register.entity.controller.place

import io.reactivex.rxjava3.core.Completable

interface RemovePlaceProduct {
  fun removePlaceProduct(placeId: Long, placeProductId: Long): Completable
}