package neuro.expenses.register.entity.mapper

import neuro.expenses.register.entity.Expense
import neuro.expenses.register.entity.Product

class ProductMapperImpl : ProductMapper {
  override fun map(productId: Long, expense: Expense): Product {
    return Product(productId, expense.description, expense.category, expense.price, expense.amount)
  }
}