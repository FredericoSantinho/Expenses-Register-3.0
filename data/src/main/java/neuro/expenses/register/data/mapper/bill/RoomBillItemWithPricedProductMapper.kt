package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.mapper.product.toDomain
import neuro.expenses.register.data.model.bill.RoomBillItemWithPlaceProduct
import neuro.expenses.register.domain.dto.BillItemDto

fun RoomBillItemWithPlaceProduct.toDomain(): BillItemDto =
  BillItemDto(roomBillItem.billItemId, roomPlaceProduct.toDomain(), roomBillItem.amount)

fun List<RoomBillItemWithPlaceProduct>.toDomain(): List<BillItemDto> = map { it.toDomain() }
