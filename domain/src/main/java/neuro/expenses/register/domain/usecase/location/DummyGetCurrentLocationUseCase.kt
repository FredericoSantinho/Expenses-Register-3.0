package neuro.expenses.register.domain.usecase.location

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.LatLngDto

class DummyGetCurrentLocationUseCase() : GetCurrentLocationUseCase {
  override fun getCurrentLocation(): Single<LatLngDto> {
    return Single.just(LatLngDto(0.0, 0.0))
  }
}