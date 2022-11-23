package neuro.expenses.register.viewmodel.bill.model

import java.util.*

data class BillModel(
  val id: Long,
  val iconUrl: String,
  val place: String,
  val time: String,
  val date: String,
  val total: String,
  val isOpen: Boolean,
  val calendar: Calendar
)