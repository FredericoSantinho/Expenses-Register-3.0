package neuro.expenses.register.ui.manual.register.mapper

import neuro.expenses.register.domain.entity.BillItem

interface BillItemMapper {
  fun map(
    description: String,
    category: String,
    place: String,
    price: String,
    amount: String
  ): BillItem
}