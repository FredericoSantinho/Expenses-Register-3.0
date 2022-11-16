package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.mapper.place.RoomPlaceWithPlaceProductsMapper
import neuro.expenses.register.data.model.bill.RoomBillWithBillItemsAndPlace
import neuro.expenses.register.domain.dto.BillDto

class RoomBillWithBillItemsMapperImpl(
  private val roomBillItemWithPlaceProductMapper: RoomBillItemWithPlaceProductMapper,
  private val roomPlaceWithPlaceProductsMapper: RoomPlaceWithPlaceProductsMapper
) :
  RoomBillWithBillItemsMapper {
  override fun map(roomBillWithBillItemsAndPlace: RoomBillWithBillItemsAndPlace): BillDto {
    val roomBill = roomBillWithBillItemsAndPlace.roomBill
    val billItemDtoList =
      roomBillWithBillItemsAndPlace.billItems.map { roomBillItemWithPlaceProductMapper.map(it) }

    val id = roomBill.billId
    val place = roomPlaceWithPlaceProductsMapper.map(roomBillWithBillItemsAndPlace.place.get(0))
    val timestamp = roomBill.calendar
    val total = roomBill.total
    val billItems = billItemDtoList
    val isOpen = roomBill.isOpen
    val iconUrl = roomBill.iconUrl

    return BillDto(id, place, timestamp, total, billItems, isOpen, iconUrl)
  }
}