package neuro.expenses.register.data.mapper.place

import neuro.expenses.register.data.model.place.RoomPlaceWithPlaceProducts
import neuro.expenses.register.domain.dto.PlaceDto

interface RoomPlaceWithPlaceProductsMapper {
  fun map(roomPlaceWithPlaceProducts: RoomPlaceWithPlaceProducts): PlaceDto
  fun map(roomPlaceWithPlaceProductslist: List<RoomPlaceWithPlaceProducts>): List<PlaceDto>
}