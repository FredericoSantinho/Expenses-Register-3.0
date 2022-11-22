package neuro.expenses.register.domain.repository

import io.reactivex.rxjava3.core.Completable

interface AddPlaceProductRepository {
  fun addPlaceProduct(placeId: Long, placeProductId: Long): Completable
}