package neuro.expenses.register.domain.entity

data class Bill(
  val place: String,
  val timestamp: Long,
  val billItems: List<BillItem> = emptyList(),
  val isOpen: Boolean = true
)