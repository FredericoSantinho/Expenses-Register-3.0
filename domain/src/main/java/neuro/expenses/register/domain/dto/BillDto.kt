package neuro.expenses.register.domain.dto

data class BillDto(
  val place: String,
  val timestamp: Long,
  val total: Double,
  val billItems: List<BillItemDto> = emptyList(),
  val isOpen: Boolean = true,
  val iconUrl: String = ""
)