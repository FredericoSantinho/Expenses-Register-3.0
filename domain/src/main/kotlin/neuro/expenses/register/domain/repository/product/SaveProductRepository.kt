package neuro.expenses.register.domain.repository.product

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.ProductDto

interface SaveProductRepository {

  /**
   * Save a product in database.
   */
  fun saveProduct(productDto: ProductDto): Completable
}