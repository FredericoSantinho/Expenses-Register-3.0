package neuro.expenses.register.entity.product

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.model.Product

interface SaveProduct {
  fun saveProduct(product: Product): Completable
}