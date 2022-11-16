package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.mapper.toDomain
import neuro.expenses.register.domain.repository.SaveProductRepository
import neuro.expenses.register.entity.PlaceProduct
import neuro.expenses.register.entity.controller.product.SaveProduct

class SaveProductImpl(
  private val saveProductRepository: SaveProductRepository
) : SaveProduct {
  override fun saveProduct(placeProduct: PlaceProduct): Completable {
    return Completable.fromAction { saveProductRepository.saveProduct(placeProduct.toDomain()) }
  }
}