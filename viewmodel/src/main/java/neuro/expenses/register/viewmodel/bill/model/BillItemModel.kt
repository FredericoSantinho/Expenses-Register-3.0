package neuro.expenses.register.viewmodel.bill.model

import androidx.compose.runtime.MutableState
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatter

data class BillItemModel(
  val id: Long,
  val description: String,
  val price: MutableState<String>,
  val amount: MutableState<String>,
  val total: MutableState<String>,
  val iconUrl: String,
  private val decimalFormatter: DecimalFormatter
) {
  fun updateTotal() {
    val priceDouble = price()
    val amountDouble = amount()
    val totalDouble = priceDouble * amountDouble
    total.value = decimalFormatter.format(totalDouble)
  }

  fun price(): Double {
    return if (price.value.isEmpty()) 0.0 else price.value.toDouble()
  }

  fun amount(): Double {
    return if (amount.value.isEmpty()) 0.0 else amount.value.toDouble()
  }
}