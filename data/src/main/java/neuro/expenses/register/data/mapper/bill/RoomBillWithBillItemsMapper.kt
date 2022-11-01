package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.model.bill.RoomBillWithBillItems
import neuro.expenses.register.domain.dto.BillDto

interface RoomBillWithBillItemsMapper {
  fun map(roomBillWithBillItems: RoomBillWithBillItems): BillDto
}