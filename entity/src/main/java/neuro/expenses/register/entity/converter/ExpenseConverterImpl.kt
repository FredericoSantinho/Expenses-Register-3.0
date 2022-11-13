package neuro.expenses.register.entity.converter

import neuro.expenses.register.entity.Expense
import neuro.expenses.register.entity.Product

class ExpenseConverterImpl : ExpenseConverter {
  override fun convertToProduct(expense: Expense, productId: Long): Product {
    return Product(productId, expense.description, expense.category, expense.price, expense.amount)
  }
}
