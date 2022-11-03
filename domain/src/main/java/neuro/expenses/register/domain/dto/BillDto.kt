package neuro.expenses.register.domain.dto

import java.util.*

data class BillDto(
  val id: Long,
  val place: String,
  val calendar: Calendar,
  val total: Double,
  val billItems: List<BillItemDto> = emptyList(),
  val isOpen: Boolean = true,
  val iconUrl: String = ""
)