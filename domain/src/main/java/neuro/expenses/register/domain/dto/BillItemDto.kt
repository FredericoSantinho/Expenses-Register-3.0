package neuro.expenses.register.domain.dto

import java.util.*

data class BillItemDto(
  val product: ProductDto,
  val amount: Double,
  val calendar: Calendar
)