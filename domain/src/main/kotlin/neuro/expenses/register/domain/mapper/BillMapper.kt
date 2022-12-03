package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.entity.model.Bill

fun Bill.toDomain() = BillDto(
  id, place.toDomain(), calendar, total, billItems.toDomain(), isOpen, iconUrl
)

fun BillDto.toEntity() = Bill(
  id, placeDto.toEntity(), calendar, total, billItemsDtos.toEntity(), iconUrl, isOpen
)
