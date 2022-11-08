package neuro.expenses.register.data.mapper.product

import neuro.expenses.register.data.model.product.RoomPricedProductWithProductAndCategory
import neuro.expenses.register.domain.dto.ProductDto

class RoomPricedProductWithProductAndCategoryMapperImpl :
  RoomPricedProductWithProductAndCategoryMapper {
  override fun map(
    roomPricedProductWithProductAndCategory: RoomPricedProductWithProductAndCategory
  ): ProductDto {
    return ProductDto(
      roomPricedProductWithProductAndCategory.roomPricedProduct.pricedProductId,
      roomPricedProductWithProductAndCategory.roomProduct.get(0).description,
      roomPricedProductWithProductAndCategory.category.get(0).name,
      roomPricedProductWithProductAndCategory.roomPricedProduct.price,
      roomPricedProductWithProductAndCategory.roomProduct.get(0).iconUrl
    )
  }
}