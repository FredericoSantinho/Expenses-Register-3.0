package neuro.expenses.register.domain.usecase.place

import neuro.expenses.register.domain.dto.LatLngDto

interface GetDistanceUseCase {
  fun getDistance(a: LatLngDto, b: LatLngDto): Double
}