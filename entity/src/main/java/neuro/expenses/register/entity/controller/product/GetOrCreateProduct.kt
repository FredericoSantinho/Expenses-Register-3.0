package neuro.expenses.register.entity.controller.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.Expense
import neuro.expenses.register.entity.Product

interface GetOrCreateProduct {
  fun getOrCreateProduct(expense: Expense): Single<Product>
}