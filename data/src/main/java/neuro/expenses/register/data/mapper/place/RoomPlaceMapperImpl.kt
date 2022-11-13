package neuro.expenses.register.data.mapper.place

import neuro.expenses.register.data.model.place.RoomPlace
import neuro.expenses.register.domain.dto.PlaceDto

class RoomPlaceMapperImpl(private val roomLatLngMapper: RoomLatLngMapper) : RoomPlaceMapper {
  override fun map(placeDto: PlaceDto): RoomPlace {
    return RoomPlace(
      placeDto.name,
      roomLatLngMapper.map(placeDto.latLngDto)
    )
  }
}