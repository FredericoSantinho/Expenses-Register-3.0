package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.mapper.place.toDomain
import neuro.expenses.register.data.model.bill.RoomBillWithBillItemsAndPlace
import neuro.expenses.register.domain.dto.BillDto

fun RoomBillWithBillItemsAndPlace.toDomain(): BillDto {
  val roomBill = roomBill

  val id = roomBill.billId
  val place = place.toDomain()
  val timestamp = roomBill.calendar
  val total = roomBill.total
  val billItems = billItems.toDomain()
  val isOpen = roomBill.isOpen
  val iconUrl = roomBill.iconUrl

  return BillDto(id, place, timestamp, total, billItems, isOpen, iconUrl)
}

fun List<RoomBillWithBillItemsAndPlace>.toDomain(): List<BillDto> = map { it -> it.toDomain() }