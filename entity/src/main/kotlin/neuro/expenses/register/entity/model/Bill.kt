package neuro.expenses.register.entity.model

import java.util.*

private val emptyPlace = Place(-1, "", emptyList(), LatLng(0.0, 0.0))

data class Bill(
  val id: Long,
  val place: Place = emptyPlace,
  val calendar: Calendar,
  val total: Double = 0.0,
  val billItems: List<BillItem> = emptyList(),
  val iconUrl: String = "",
  val isOpen: Boolean = true
)