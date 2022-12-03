package neuro.expenses.register.data.mapper.product

import neuro.expenses.register.data.mapper.category.toDomain
import neuro.expenses.register.data.model.placeproduct.RoomPlaceProduct
import neuro.expenses.register.data.model.placeproduct.RoomPlaceProductWithProductAndCategory
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.dto.ProductDto

fun RoomPlaceProductWithProductAndCategory.toDomain(): PlaceProductDto {
  return PlaceProductDto(
    roomPlaceProduct.placeProductId, ProductDto(
      roomProduct.productId,
      roomProduct.description,
      roomProduct.variableAmount,
      roomProduct.iconUrl
    ), category.toDomain(), roomPlaceProduct.price
  )
}

fun PlaceProductDto.toData() = RoomPlaceProduct(id, productDto.id, category.id, price)
