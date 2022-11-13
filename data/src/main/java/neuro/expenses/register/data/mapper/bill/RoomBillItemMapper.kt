package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.model.bill.RoomBillItem
import neuro.expenses.register.domain.dto.BillItemDto

interface RoomBillItemMapper {
  fun map(billItemDto: BillItemDto, billId: Long, placeProductId: Long): RoomBillItem
}