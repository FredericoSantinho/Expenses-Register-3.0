package neuro.expenses.register.data.mapper.product

import neuro.expenses.register.data.mapper.category.toDomain
import neuro.expenses.register.data.model.product.RoomPlaceProductWithProductAndCategory
import neuro.expenses.register.domain.dto.ProductDto

fun RoomPlaceProductWithProductAndCategory.toDomain(): ProductDto = ProductDto(
  roomPlaceProduct.placeProductId,
  roomProduct.get(0).description,
  category.get(0).toDomain(),
  roomPlaceProduct.price,
  roomPlaceProduct.defaultAmount,
  roomProduct.get(0).iconUrl,
  roomPlaceProduct.placeId
)

