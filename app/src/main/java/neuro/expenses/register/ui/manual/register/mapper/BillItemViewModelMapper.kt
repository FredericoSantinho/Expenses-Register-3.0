package neuro.expenses.register.ui.manual.register.mapper

import neuro.expenses.register.domain.dto.BillItemDto
import java.util.*


interface BillItemViewModelMapper {
  fun map(
    description: String,
    category: String,
    place: String,
    price: String,
    amount: String,
    calendar: Calendar
  ): BillItemDto
}