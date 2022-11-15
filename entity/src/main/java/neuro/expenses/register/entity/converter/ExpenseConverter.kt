package neuro.expenses.register.entity.converter

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.Expense
import neuro.expenses.register.entity.Product

interface ExpenseConverter {
  fun convertToProduct(expense: Expense, productId: Long = 0L): Single<Product>
}