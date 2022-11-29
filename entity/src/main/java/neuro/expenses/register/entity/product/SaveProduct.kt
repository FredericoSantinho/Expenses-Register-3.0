package neuro.expenses.register.entity.product

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.model.Product

interface SaveProduct {
  /**
   * Save Product.
   *
   * @param product Product to save.
   * @return Completable that completes if operation succeeds.
   */
  fun saveProduct(product: Product): Completable
}