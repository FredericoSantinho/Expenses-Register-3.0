package neuro.expenses.register.data.mapper.product

import neuro.expenses.register.data.mapper.category.toDomain
import neuro.expenses.register.data.model.product.RoomPlaceProductWithProductAndCategory
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.dto.ProductDto

fun RoomPlaceProductWithProductAndCategory.toDomain(): PlaceProductDto {
  val roomProduct = roomProduct.get(0)

  return PlaceProductDto(
    roomPlaceProduct.placeProductId,
    ProductDto(
      roomProduct.productId,
      roomProduct.description,
      roomProduct.variableAmount,
      roomProduct.iconUrl
    ),
    category.get(0).toDomain(),
    roomPlaceProduct.price,
    roomPlaceProduct.placeId
  )
}

