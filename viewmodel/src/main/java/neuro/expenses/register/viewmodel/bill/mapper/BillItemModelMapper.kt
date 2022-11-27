package neuro.expenses.register.viewmodel.bill.mapper

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.viewmodel.bill.model.BillItemModel
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatter

class BillItemModelMapper(private val decimalFormatter: DecimalFormatter) {
  fun map(billItemDto: BillItemDto): BillItemModel {
    val id = billItemDto.id
    val description = billItemDto.placeProductDto.productDto.description
    val price = mutableStateOf(decimalFormatter.format(billItemDto.placeProductDto.price))
    val amount = mutableStateOf(
      if (billItemDto.amount % 1.00 == 0.0) billItemDto.amount.toInt()
        .toString() else decimalFormatter.format(billItemDto.amount)
    )
    val total = mutableStateOf(decimalFormatter.format(billItemDto.total))
    val iconUrl = billItemDto.placeProductDto.productDto.iconUrl

    return BillItemModel(id, description, price, amount, total, iconUrl, decimalFormatter)
  }
}
