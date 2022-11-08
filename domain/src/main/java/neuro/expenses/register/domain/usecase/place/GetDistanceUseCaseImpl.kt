package neuro.expenses.register.domain.usecase.place

import neuro.expenses.register.domain.dto.LatLngDto

class GetDistanceUseCaseImpl : GetDistanceUseCase {
  override fun getDistance(a: LatLngDto, b: LatLngDto): Double {
    return 10.0
  }
}