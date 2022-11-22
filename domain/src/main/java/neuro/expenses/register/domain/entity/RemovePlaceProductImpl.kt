package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.repository.place.RemovePlaceProductRepository
import neuro.expenses.register.entity.controller.place.RemovePlaceProduct

class RemovePlaceProductImpl(private val removePlaceProductRepository: RemovePlaceProductRepository) :
  RemovePlaceProduct {
  override fun removePlaceProduct(placeId: Long, placeProductId: Long): Completable {
    return removePlaceProductRepository.removePlaceProduct(placeId, placeProductId)
  }
}