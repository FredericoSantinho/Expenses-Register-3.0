package neuro.expenses.register.entity.location

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.model.LatLng

interface GetCurrentLocation {
  /**
   * Get current location.
   *
   * @return Single with the current location.
   */
  fun getCurrentLocation(): Single<LatLng>
}