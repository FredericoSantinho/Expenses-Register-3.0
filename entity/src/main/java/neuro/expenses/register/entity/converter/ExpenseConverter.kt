package neuro.expenses.register.entity.converter

import neuro.expenses.register.entity.Expense
import neuro.expenses.register.entity.Product

interface ExpenseConverter {
  fun convertToProduct(expense: Expense, productId: Long = 0L): Product
}