package neuro.expenses.register.viewmodel.edit.bill.model

data class BillItemModel(
  val id: Long,
  val description: String,
  val price: String,
  val amount: String,
  val total: String,
  val iconUrl: String
)