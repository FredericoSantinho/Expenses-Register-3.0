package neuro.expenses.register.domain.entity

data class Bill(
  val place: String,
  val timestamp: Long,
  val total: Double = 0.0,
  val billItems: List<BillItem> = emptyList(),
  val isOpen: Boolean = true,
  val iconUrl: String = ""
)