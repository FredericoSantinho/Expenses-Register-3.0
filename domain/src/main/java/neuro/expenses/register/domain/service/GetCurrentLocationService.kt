package neuro.expenses.register.domain.service

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.LatLngDto

interface GetCurrentLocationService {
  fun getCurrentLocation(): Maybe<LatLngDto>
}