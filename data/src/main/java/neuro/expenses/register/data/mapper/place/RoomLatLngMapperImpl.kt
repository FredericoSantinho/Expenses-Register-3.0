package neuro.expenses.register.data.mapper.place

import neuro.expenses.register.data.model.place.LatLng
import neuro.expenses.register.domain.dto.LatLngDto

class RoomLatLngMapperImpl : RoomLatLngMapper {
  override fun map(latLngDto: LatLngDto): LatLng {
    return LatLng(latLngDto.latitude, latLngDto.longitude)
  }

  override fun map(latLng: LatLng): LatLngDto {
    return LatLngDto(latLng.latitude, latLng.longitude)
  }
}