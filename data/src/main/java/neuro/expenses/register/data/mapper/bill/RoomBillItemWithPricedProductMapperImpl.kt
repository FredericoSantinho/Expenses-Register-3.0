package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.mapper.product.RoomPlaceProductWithProductAndCategoryMapper
import neuro.expenses.register.data.model.bill.RoomBillItemWithPlaceProduct
import neuro.expenses.register.domain.dto.BillItemDto

class RoomBillItemWithPlaceProductMapperImpl(private val roomPlaceProductWithProductAndCategoryMapper: RoomPlaceProductWithProductAndCategoryMapper) :
  RoomBillItemWithPlaceProductMapper {
  override fun map(roomBillItemWithPlaceProduct: RoomBillItemWithPlaceProduct): BillItemDto {
    val roomPlaceProductWithProductAndCategory =
      roomBillItemWithPlaceProduct.roomPlaceProduct.get(0)

    return BillItemDto(
      roomBillItemWithPlaceProduct.roomBillItem.billItemId,
      roomPlaceProductWithProductAndCategoryMapper.map(
        roomPlaceProductWithProductAndCategory
      ),
      roomBillItemWithPlaceProduct.roomBillItem.amount
    )
  }
}