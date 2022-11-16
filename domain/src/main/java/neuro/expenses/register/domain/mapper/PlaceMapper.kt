package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.entity.Place

fun Place.toDomain(): PlaceDto = PlaceDto(id, name, products.toDomain(), latLng.toDomain())

fun PlaceDto.toEntity(): Place = Place(id, name, products.toEntity(), latLngDto.toEntity())
