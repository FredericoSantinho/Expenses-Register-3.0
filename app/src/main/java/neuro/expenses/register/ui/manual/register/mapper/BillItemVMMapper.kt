package neuro.expenses.register.ui.manual.register.mapper

import neuro.expenses.register.domain.dto.BillItemDto


interface BillItemVMMapper {
  fun map(
    description: String,
    category: String,
    place: String,
    price: String,
    amount: String
  ): BillItemDto
}