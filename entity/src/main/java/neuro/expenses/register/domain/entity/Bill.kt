package neuro.expenses.register.domain.entity

import java.util.*

data class Bill(
  val id: Long,
  val place: String,
  val calendar: Calendar,
  val total: Double = 0.0,
  val billItems: List<BillItem> = emptyList(),
  val isOpen: Boolean = true,
  val iconUrl: String = ""
)