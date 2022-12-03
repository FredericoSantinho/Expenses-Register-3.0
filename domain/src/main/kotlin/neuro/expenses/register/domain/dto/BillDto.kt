package neuro.expenses.register.domain.dto

import java.util.*

data class BillDto(
  val id: Long,
  val placeDto: PlaceDto,
  val calendar: Calendar,
  val total: Double,
  val billItemsDtos: List<BillItemDto> = emptyList(),
  val isOpen: Boolean,
  val iconUrl: String = ""
)