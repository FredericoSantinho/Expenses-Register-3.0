package neuro.expenses.register.domain.entity.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.repository.place.AddPlaceProductRepository
import neuro.expenses.register.entity.place.AddPlaceProduct

class AddPlaceProductImpl(private val addPlaceProductRepository: AddPlaceProductRepository) :
  AddPlaceProduct {
  override fun addPlaceProduct(placeId: Long, placeProductId: Long): Completable {
    return addPlaceProductRepository.addPlaceProduct(placeId, placeProductId)
  }
}