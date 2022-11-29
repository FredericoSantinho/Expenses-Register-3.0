package neuro.expenses.register.domain.repository.place

import io.reactivex.rxjava3.core.Completable

interface RemovePlaceProductRepository {
  fun removePlaceProduct(placeId: Long, placeProductId: Long): Completable
}