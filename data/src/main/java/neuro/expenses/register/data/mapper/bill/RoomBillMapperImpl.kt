package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.model.bill.RoomBill
import neuro.expenses.register.domain.dto.BillDto

class RoomBillMapperImpl : RoomBillMapper {
  override fun map(billDto: BillDto): RoomBill {
    val id = billDto.id
    val place = billDto.place
    val timestamp = billDto.timestamp
    val total = billDto.total
    val isOpen = billDto.isOpen
    val iconUrl = billDto.iconUrl

    return RoomBill(id, place, timestamp, total, isOpen, iconUrl)
  }
}