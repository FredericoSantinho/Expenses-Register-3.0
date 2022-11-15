package neuro.expenses.register.data.mapper.product

import neuro.expenses.register.data.mapper.category.RoomCategoryMapper
import neuro.expenses.register.data.model.product.RoomPlaceProductWithProductAndCategory
import neuro.expenses.register.domain.dto.ProductDto

class RoomPlaceProductWithProductAndCategoryMapperImpl(private val roomCategoryMapper: RoomCategoryMapper) :
  RoomPlaceProductWithProductAndCategoryMapper {
  override fun map(
    roomPlaceProductWithProductAndCategory: RoomPlaceProductWithProductAndCategory
  ): ProductDto {
    return ProductDto(
      roomPlaceProductWithProductAndCategory.roomPlaceProduct.placeProductId,
      roomPlaceProductWithProductAndCategory.roomProduct.get(0).description,
      roomCategoryMapper.map(roomPlaceProductWithProductAndCategory.category.get(0)),
      roomPlaceProductWithProductAndCategory.roomPlaceProduct.price,
      roomPlaceProductWithProductAndCategory.roomPlaceProduct.defaultAmount,
      roomPlaceProductWithProductAndCategory.roomProduct.get(0).iconUrl
    )
  }
}