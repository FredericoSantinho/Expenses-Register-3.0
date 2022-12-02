package neuro.expenses.register.domain.usecase.location

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.LatLngDto

interface GetCurrentLocationUseCase {
  fun getCurrentLocation(): Single<LatLngDto>
}