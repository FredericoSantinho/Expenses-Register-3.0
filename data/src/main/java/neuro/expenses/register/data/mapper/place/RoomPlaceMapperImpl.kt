package neuro.expenses.register.data.mapper.place

import neuro.expenses.register.data.model.place.RoomPlace
import neuro.expenses.register.domain.dto.PlaceDto

class RoomPlaceMapperImpl(private val latLngMapper: LatLngMapper) : RoomPlaceMapper {
  override fun map(placeDto: PlaceDto): RoomPlace {
    return RoomPlace(placeDto.name, latLngMapper.map(placeDto.latLng))
  }
}