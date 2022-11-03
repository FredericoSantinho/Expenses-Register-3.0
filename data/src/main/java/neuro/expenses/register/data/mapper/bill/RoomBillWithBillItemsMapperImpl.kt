package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.model.bill.RoomBillWithBillItems
import neuro.expenses.register.domain.dto.BillDto

class RoomBillWithBillItemsMapperImpl(private val roomBillItemWithPricedProductMapper: RoomBillItemWithPricedProductMapper) :
  RoomBillWithBillItemsMapper {
  override fun map(roomBillWithBillItems: RoomBillWithBillItems): BillDto {
    val roomBill = roomBillWithBillItems.roomBill
    val billItemDtoList =
      roomBillWithBillItems.billItems.map { roomBillItemWithPricedProductMapper.map(it) }

    val id = roomBill.billId
    val place = roomBill.place
    val timestamp = roomBill.timestamp
    val total = roomBill.total
    val billItems = billItemDtoList
    val isOpen = roomBill.isOpen
    val iconUrl = roomBill.iconUrl

    return BillDto(id, place, timestamp, total, billItems, isOpen, iconUrl)
  }
}