package neuro.expenses.register.entity.converter

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.Category
import neuro.expenses.register.entity.Expense
import neuro.expenses.register.entity.Product
import neuro.expenses.register.entity.controller.GetCategoryId

class ExpenseConverterImpl(private val getCategoryId: GetCategoryId) : ExpenseConverter {
  override fun convertToProduct(expense: Expense, productId: Long): Single<Product> {
    return getCategoryId.getCategoryId(expense.category.lowercase()).map {
      Product(
        productId,
        expense.description,
        Category(it, expense.category),
        expense.price,
        expense.amount
      )
    }
  }
}
