package neuro.expenses.register.domain.usecase.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.entity.Expense
import neuro.expenses.register.domain.entity.Product

interface GetOrCreateProductUseCase {
  fun getOrCreateProduct(expense: Expense): Single<Product>
}