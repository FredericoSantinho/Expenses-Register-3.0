package neuro.expenses.register.data.mapper.product

import neuro.expenses.register.data.model.bill.RoomPricedProductWithProductAndCategory
import neuro.expenses.register.domain.dto.ProductDto

interface RoomPricedProductWithProductAndCategoryMapper {
  fun map(roomPricedProductWithProductAndCategory: RoomPricedProductWithProductAndCategory): ProductDto
}