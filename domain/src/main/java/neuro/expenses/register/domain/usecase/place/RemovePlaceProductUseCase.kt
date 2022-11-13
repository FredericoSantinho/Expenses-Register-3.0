package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable

interface RemovePlaceProductUseCase {
  fun removePlaceProduct(placeId: Long, productId: Long): Completable
}