package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.entity.model.Place

fun Place.toDomain(): PlaceDto = PlaceDto(id, name, placeProducts.toDomain(), latLng.toDomain())
fun List<Place>.toDomain(): List<PlaceDto> = map { it.toDomain() }

fun PlaceDto.toEntity(): Place = Place(id, name, products.toEntity(), latLngDto.toEntity())
fun List<PlaceDto>.toEntity(): List<Place> = map { it.toEntity() }
