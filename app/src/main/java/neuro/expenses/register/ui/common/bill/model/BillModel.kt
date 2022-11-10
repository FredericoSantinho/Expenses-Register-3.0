package neuro.expenses.register.ui.common.bill.model

data class BillModel(
  val id: Long,
  val iconUrl: String,
  val place: String,
  val time: String,
  val date: String,
  val total: String,
  val isOpen: Boolean,
)