package neuro.expenses.register.domain.repository.product

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.ProductDto

interface SaveProductRepository {
  /**
   * Save Product.
   *
   * @param productDto Product to save.
   * @return Completable that completes if operation succeeds.
   */
  fun saveProduct(productDto: ProductDto): Completable
}