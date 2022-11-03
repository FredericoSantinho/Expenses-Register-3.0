package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.model.bill.RoomBill
import neuro.expenses.register.domain.dto.BillDto

interface RoomBillMapper {
  fun map(billDto: BillDto): RoomBill
}