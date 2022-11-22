package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.entity.PlaceProduct

fun PlaceProduct.toDomain() =
  PlaceProductDto(
    id, product.toDomain(),
    category.toDomain(), price
  )

fun List<PlaceProduct>.toDomain() = map { it.toDomain() }

fun PlaceProductDto.toEntity() =
  PlaceProduct(
    id, productDto.toEntity(),
    category.toEntity(), price
  )

fun List<PlaceProductDto>.toEntity() = map { it.toEntity() }