package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.mapper.product.RoomPricedProductWithProductAndCategoryMapper
import neuro.expenses.register.data.model.bill.RoomBillItemWithPricedProduct
import neuro.expenses.register.domain.dto.BillItemDto

class RoomBillItemWithPricedProductMapperImpl(private val roomPricedProductWithProductAndCategoryMapper: RoomPricedProductWithProductAndCategoryMapper) :
  RoomBillItemWithPricedProductMapper {
  override fun map(roomBillItem: RoomBillItemWithPricedProduct): BillItemDto {
    val roomPricedProductWithProductAndCategory = roomBillItem.roomPricedProduct.get(0)

    return BillItemDto(
      roomPricedProductWithProductAndCategoryMapper.map(
        roomPricedProductWithProductAndCategory
      ), roomBillItem.roomBillItem.amount
    )
  }

  override fun map(roomBillItems: List<RoomBillItemWithPricedProduct>): List<BillItemDto> {
    return roomBillItems.map { map(it) }
  }
}