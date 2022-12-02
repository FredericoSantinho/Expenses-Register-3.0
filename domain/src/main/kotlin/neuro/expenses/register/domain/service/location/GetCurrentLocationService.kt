package neuro.expenses.register.domain.service.location

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.LatLngDto

interface GetCurrentLocationService {
  /**
   * Get current location.
   *
   * @throws NoLocationPermissionException in case there's no permission to get current location.
   * @throws NoLocationException in case it's not possible to retrieve the location (eg. location disabled).
   */
  fun getCurrentLocation(): Single<LatLngDto>
}