package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.model.bill.RoomBillWithBillItemsAndPlace
import neuro.expenses.register.domain.dto.BillDto

interface RoomBillWithBillItemsMapper {
  fun map(roomBillWithBillItemsAndPlace: RoomBillWithBillItemsAndPlace): BillDto
}