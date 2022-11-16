package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.model.bill.RoomBill
import neuro.expenses.register.domain.dto.BillDto

class RoomBillMapperImpl() : RoomBillMapper {
  override fun map(billDto: BillDto): RoomBill {
    val id = billDto.id
    val placeId = billDto.place.id
    val calendar = billDto.calendar
    val total = billDto.total
    val isOpen = billDto.isOpen
    val iconUrl = billDto.iconUrl

    return RoomBill(id, placeId, calendar, total, isOpen, iconUrl)
  }
}