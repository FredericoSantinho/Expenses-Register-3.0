package neuro.expenses.register.data.mapper.place

import neuro.expenses.register.data.mapper.product.toDomain
import neuro.expenses.register.data.model.place.RoomPlaceWithPlaceProducts
import neuro.expenses.register.domain.dto.PlaceDto

fun RoomPlaceWithPlaceProducts.toDomain(): PlaceDto = PlaceDto(
  roomPlace.placeId,
  roomPlace.name,
  PlaceProducts.map { it.toDomain() },
  roomPlace.latLng.toDomain()
)

fun List<RoomPlaceWithPlaceProducts>.toDomain(): List<PlaceDto> = map { it.toDomain() }