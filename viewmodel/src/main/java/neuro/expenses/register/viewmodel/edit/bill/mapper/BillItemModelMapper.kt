package neuro.expenses.register.viewmodel.edit.bill.mapper

import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatter
import neuro.expenses.register.viewmodel.edit.bill.model.BillItemModel

class BillItemModelMapper(
  private val decimalFormatter: DecimalFormatter, private val currency: String
) {
  fun map(billItemDto: BillItemDto): BillItemModel {
    val id = billItemDto.id
    val description = billItemDto.placeProductDto.productDto.description
    val price = decimalFormatter.format(billItemDto.placeProductDto.price) + ' ' + currency
    val amount = if (billItemDto.amount % 1.00 == 0.0) billItemDto.amount.toInt()
      .toString() else decimalFormatter.format(billItemDto.amount)
    val total = decimalFormatter.format(billItemDto.total) + ' ' + currency
    val iconUrl = billItemDto.placeProductDto.productDto.iconUrl

    return BillItemModel(id, description, price, amount, total, iconUrl)
  }
}
