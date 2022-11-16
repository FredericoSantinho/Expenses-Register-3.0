package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.entity.Product

fun Product.toDomain() =
  ProductDto(id, description, category.toDomain(), price, iconUrl, placeId, variableAmount)

fun List<Product>.toDomain() = map { it.toDomain() }

fun ProductDto.toEntity() =
  Product(id, description, category.toEntity(), price, placeId, variableAmount, iconUrl)

fun List<ProductDto>.toEntity() = map { it.toEntity() }