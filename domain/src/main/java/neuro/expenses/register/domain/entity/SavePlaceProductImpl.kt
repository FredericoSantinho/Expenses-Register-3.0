package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.mapper.toDomain
import neuro.expenses.register.domain.repository.SavePlaceProductRepository
import neuro.expenses.register.entity.PlaceProduct
import neuro.expenses.register.entity.controller.product.SavePlaceProduct

class SavePlaceProductImpl(private val savePlaceProductRepository: SavePlaceProductRepository) :
  SavePlaceProduct {
  override fun savePlaceProduct(placeProduct: PlaceProduct): Completable {
    return savePlaceProductRepository.savePlaceProduct(placeProduct.toDomain())
  }
}