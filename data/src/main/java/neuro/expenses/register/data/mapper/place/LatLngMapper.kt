package neuro.expenses.register.data.mapper.place

import neuro.expenses.register.data.model.place.LatLng
import neuro.expenses.register.domain.dto.LatLngDto

interface LatLngMapper {
  fun map(latLngDto: LatLngDto): LatLng
  fun map(latLng: LatLng): LatLngDto
}