package neuro.expenses.register.data.mapper.product

import neuro.expenses.register.data.model.bill.RoomPricedProductWithProductAndCategory
import neuro.expenses.register.domain.dto.PricedProductDto

interface RoomPricedProductWithProductAndCategoryMapper {
  fun map(roomPricedProductWithProductAndCategory: RoomPricedProductWithProductAndCategory): PricedProductDto
}