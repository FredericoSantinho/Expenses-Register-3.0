package neuro.expenses.register.entity.mocks

import neuro.expenses.register.domain.dto.LatLngDto

fun latLngDtoMock(latitude: Double = 1.0, longitude: Double = 2.0): LatLngDto {
  return LatLngDto(latitude, longitude)
}