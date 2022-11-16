package neuro.expenses.register.entity.controller.product

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.Product

interface SaveProduct {
  fun saveProduct(product: Product): Completable
}