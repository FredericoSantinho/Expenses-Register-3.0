package neuro.expenses.register.data.mapper.product

import neuro.expenses.register.data.model.product.RoomPlaceProductWithProductAndCategory
import neuro.expenses.register.domain.dto.ProductDto

interface RoomPlaceProductWithProductAndCategoryMapper {
  fun map(roomPlaceProductWithProductAndCategory: RoomPlaceProductWithProductAndCategory): ProductDto
}