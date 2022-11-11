package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.entity.LatLng

class LatLngMapperImpl : LatLngMapper {
  override fun map(latLng: LatLng): LatLngDto {
    return LatLngDto(latLng.latitude, latLng.longitude)
  }
}