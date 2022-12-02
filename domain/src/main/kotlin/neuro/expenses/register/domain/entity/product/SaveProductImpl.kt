package neuro.expenses.register.domain.entity.product

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.mapper.toDomain
import neuro.expenses.register.domain.repository.product.SaveProductRepository
import neuro.expenses.register.entity.model.Product
import neuro.expenses.register.entity.product.SaveProduct

class SaveProductImpl(private val saveProductRepository: SaveProductRepository) : SaveProduct {
  override fun saveProduct(product: Product): Completable {
    return saveProductRepository.saveProduct(product.toDomain())
  }
}