package neuro.expenses.register.ui.manual.register.mapper

import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.domain.dto.ProductDto
import java.util.*


class BillItemViewModelMapperImpl : BillItemViewModelMapper {

  override fun map(
    description: String,
    category: String,
    place: String,
    price: String,
    amount: String,
    calendar: Calendar
  ): BillItemDto {
    val priceDouble: Double = if (price.isNotEmpty()) price.toDouble() else 0.0
    val amountDouble: Double = if (amount.isNotEmpty()) amount.toDouble() else 0.0

    val product = ProductDto(description, category, priceDouble)
    val billItem = BillItemDto(product, place, amountDouble, calendar)
    return billItem
  }
}