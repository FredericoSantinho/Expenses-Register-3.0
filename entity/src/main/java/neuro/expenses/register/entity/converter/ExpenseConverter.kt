package neuro.expenses.register.entity.converter

import neuro.expenses.register.entity.Expense
import neuro.expenses.register.entity.LatLng
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.Product

private val zero = LatLng(0.0, 0.0)

interface ExpenseConverter {
  fun convertToProduct(expense: Expense, productId: Long = 0L): Product
  fun convertToPlace(expense: Expense, productId: Long = 0L, latLng: LatLng = zero): Place
}