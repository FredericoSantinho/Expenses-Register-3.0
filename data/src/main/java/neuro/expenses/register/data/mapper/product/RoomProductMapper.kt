package neuro.expenses.register.data.mapper.product

import neuro.expenses.register.data.model.product.RoomProduct
import neuro.expenses.register.domain.dto.ProductDto

fun RoomProduct.toDomain(): ProductDto {
  return ProductDto(productId, description, variableAmount, iconUrl)
}

fun ProductDto.toData(): RoomProduct {
  return RoomProduct(id, description, iconUrl, variableAmount)
}

