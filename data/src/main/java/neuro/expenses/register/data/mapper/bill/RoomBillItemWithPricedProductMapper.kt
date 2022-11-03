package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.model.bill.RoomBillItemWithPricedProduct
import neuro.expenses.register.domain.dto.BillItemDto

interface RoomBillItemWithPricedProductMapper {
  fun map(roomBillItemWithPricedProduct: RoomBillItemWithPricedProduct): BillItemDto
}