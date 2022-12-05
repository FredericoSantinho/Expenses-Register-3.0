package neuro.expenses.register.domain.entity.placeproduct

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.mapper.toDomain
import neuro.expenses.register.domain.repository.placeproduct.SavePlaceProductRepository
import neuro.expenses.register.entity.model.PlaceProduct
import neuro.expenses.register.entity.placeproduct.SavePlaceProduct

class SavePlaceProductImpl(private val savePlaceProductRepository: SavePlaceProductRepository) :
  SavePlaceProduct {
  override fun savePlaceProduct(placeProduct: PlaceProduct): Completable {
    return savePlaceProductRepository.savePlaceProduct(placeProduct.toDomain())
  }
}