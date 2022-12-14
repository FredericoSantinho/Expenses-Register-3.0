package neuro.expenses.register.domain.usecase.location

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.service.location.GetCurrentLocationService

class GetCurrentLocationUseCaseImpl(private val getCurrentLocationService: GetCurrentLocationService) :
  GetCurrentLocationUseCase {

  override fun getCurrentLocation(): Single<LatLngDto> {
    return getCurrentLocationService.getCurrentLocation()
  }
}