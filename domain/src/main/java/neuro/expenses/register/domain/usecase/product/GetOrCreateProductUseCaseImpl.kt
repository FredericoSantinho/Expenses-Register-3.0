package neuro.expenses.register.domain.usecase.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.entity.Expense
import neuro.expenses.register.domain.entity.Product

class GetOrCreateProductUseCaseImpl : GetOrCreateProductUseCase {
  override fun getOrCreateProduct(expense: Expense): Single<Product> {
    return Single.just(Product(0, expense.description, expense.category, expense.price))
  }
}