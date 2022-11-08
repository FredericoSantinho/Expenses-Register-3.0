package neuro.expenses.register.data.mapper.place

import neuro.expenses.register.data.model.place.RoomPlace
import neuro.expenses.register.domain.dto.PlaceDto

interface RoomPlaceMapper {
  fun map(placeDto: PlaceDto): RoomPlace
}