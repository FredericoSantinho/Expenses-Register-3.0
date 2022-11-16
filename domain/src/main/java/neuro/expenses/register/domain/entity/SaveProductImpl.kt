package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.mapper.ProductMapper
import neuro.expenses.register.domain.repository.SaveProductRepository
import neuro.expenses.register.entity.Product
import neuro.expenses.register.entity.controller.product.SaveProduct

class SaveProductImpl(
  private val saveProductRepository: SaveProductRepository,
  private val productMapper: ProductMapper
) : SaveProduct {
  override fun saveProduct(product: Product): Completable {
    return Completable.fromAction { saveProductRepository.saveProduct(productMapper.map(product)) }
  }
}