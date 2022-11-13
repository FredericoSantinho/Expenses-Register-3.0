package neuro.expenses.register.domain.repository

import io.reactivex.rxjava3.core.Completable

interface RemovePlaceProductRepository {
  fun removePlaceProduct(placeId: Long, productId: Long): Completable
}