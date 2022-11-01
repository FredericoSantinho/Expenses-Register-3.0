package neuro.expenses.register.data.mapper.product

import neuro.expenses.register.data.model.bill.RoomPricedProductWithProductAndCategory
import neuro.expenses.register.domain.dto.PricedProductDto

class RoomPricedProductWithProductAndCategoryMapperImpl :
  RoomPricedProductWithProductAndCategoryMapper {
  override fun map(
    roomPricedProductWithProductAndCategory: RoomPricedProductWithProductAndCategory
  ): PricedProductDto {
    return PricedProductDto(
      roomPricedProductWithProductAndCategory.roomProduct.get(0).description,
      roomPricedProductWithProductAndCategory.category.get(0).name,
      roomPricedProductWithProductAndCategory.roomPricedProduct.price,
      roomPricedProductWithProductAndCategory.roomProduct.get(0).iconUrl
    )
  }
}