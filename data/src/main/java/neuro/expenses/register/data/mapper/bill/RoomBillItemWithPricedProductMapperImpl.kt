package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.mapper.product.RoomPricedProductWithProductAndCategoryMapper
import neuro.expenses.register.data.model.bill.RoomBillItemWithPricedProduct
import neuro.expenses.register.domain.dto.BillItemDto

class RoomBillItemWithPricedProductMapperImpl(private val roomPricedProductWithProductAndCategoryMapper: RoomPricedProductWithProductAndCategoryMapper) :
  RoomBillItemWithPricedProductMapper {
  override fun map(roomBillItemWithPricedProduct: RoomBillItemWithPricedProduct): BillItemDto {
    val roomPricedProductWithProductAndCategory =
      roomBillItemWithPricedProduct.roomPricedProduct.get(0)

    return BillItemDto(
      roomBillItemWithPricedProduct.roomBillItem.billItemId,
      roomPricedProductWithProductAndCategoryMapper.map(
        roomPricedProductWithProductAndCategory
      ),
      roomBillItemWithPricedProduct.roomBillItem.amount
    )
  }
}