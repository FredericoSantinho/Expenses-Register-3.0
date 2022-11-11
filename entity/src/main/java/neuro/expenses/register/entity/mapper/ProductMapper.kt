package neuro.expenses.register.entity.mapper

import neuro.expenses.register.entity.Expense
import neuro.expenses.register.entity.Product

interface ProductMapper {
  fun map(productId: Long, expense: Expense): Product
}