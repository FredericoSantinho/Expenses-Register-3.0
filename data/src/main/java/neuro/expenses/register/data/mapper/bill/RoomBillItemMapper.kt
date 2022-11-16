package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.model.bill.RoomBillItem
import neuro.expenses.register.domain.dto.BillItemDto

fun BillItemDto.toData(billId: Long): RoomBillItem =
  RoomBillItem(id, amount, placeProduct.id, billId)

fun List<BillItemDto>.toData(billId: Long): List<RoomBillItem> = map { it.toData(billId) }