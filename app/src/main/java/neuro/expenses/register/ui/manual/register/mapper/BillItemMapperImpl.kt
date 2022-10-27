package neuro.expenses.register.ui.manual.register.mapper

import neuro.expenses.register.domain.entity.BillItem
import neuro.expenses.register.domain.entity.Product

class BillItemMapperImpl : BillItemMapper {

  override fun map(
    description: String,
    category: String,
    place: String,
    price: String,
    amount: String
  ): BillItem {
    val priceDouble: Double = if (price.isNotEmpty()) price.toDouble() else 0.0
    val amountDouble: Double = if (amount.isNotEmpty()) amount.toDouble() else 0.0

    val product = Product(description, category, priceDouble)
    val billItem = BillItem(product, place, amountDouble)
    return billItem
  }
}