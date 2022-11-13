package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.model.bill.RoomBillItemWithPlaceProduct
import neuro.expenses.register.domain.dto.BillItemDto

interface RoomBillItemWithPlaceProductMapper {
  fun map(roomBillItemWithPlaceProduct: RoomBillItemWithPlaceProduct): BillItemDto
}