package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.entity.model.Product

fun Product.toDomain() =
  ProductDto(id, description, variableAmount, iconUrl)

fun List<Product>.toDomain() = map { it.toDomain() }

fun ProductDto.toEntity() =
  Product(id, description, variableAmount, iconUrl)

fun List<ProductDto>.toEntity() = map { it.toEntity() }