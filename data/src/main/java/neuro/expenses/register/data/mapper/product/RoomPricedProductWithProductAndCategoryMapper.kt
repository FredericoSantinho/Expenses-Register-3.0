package neuro.expenses.register.data.mapper.product

import neuro.expenses.register.data.mapper.category.toDomain
import neuro.expenses.register.data.model.product.RoomPlaceProductWithProductAndCategory
import neuro.expenses.register.domain.dto.ProductDto

fun RoomPlaceProductWithProductAndCategory.toDomain(): ProductDto {
  val roomProduct = roomProduct.get(0)

  return ProductDto(
    roomPlaceProduct.placeProductId,
    roomProduct.description,
    category.get(0).toDomain(),
    roomPlaceProduct.price,
    roomProduct.iconUrl,
    roomPlaceProduct.placeId,
    roomProduct.variableAmount
  )
}

