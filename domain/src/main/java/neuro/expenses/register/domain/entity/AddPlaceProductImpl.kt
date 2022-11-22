package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.repository.AddPlaceProductRepository
import neuro.expenses.register.entity.controller.place.AddPlaceProduct

class AddPlaceProductImpl(private val addPlaceProductRepository: AddPlaceProductRepository) :
  AddPlaceProduct {
  override fun addPlaceProduct(placeId: Long, placeProductId: Long): Completable {
    return addPlaceProductRepository.addPlaceProduct(placeId, placeProductId)
  }
}