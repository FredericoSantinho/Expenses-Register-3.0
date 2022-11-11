package neuro.expenses.register.entity.converter

import neuro.expenses.register.entity.Expense
import neuro.expenses.register.entity.LatLng
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.Product
import java.util.*

class ExpenseConverterImpl : ExpenseConverter {
  override fun convertToPlace(expense: Expense, productId: Long, latLng: LatLng): Place {
    val products = Collections.singletonList(convertToProduct(expense, productId))
    return Place(expense.place, products, latLng)
  }

  override fun convertToProduct(expense: Expense, productId: Long): Product {
    return Product(productId, expense.description, expense.category, expense.price, expense.amount)
  }
}
