package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.entity.LatLng

interface LatLngMapper {
  fun map(latLng: LatLng): LatLngDto
}