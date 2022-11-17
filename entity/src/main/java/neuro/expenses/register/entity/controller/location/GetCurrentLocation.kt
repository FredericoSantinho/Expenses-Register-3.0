package neuro.expenses.register.entity.controller.location

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.LatLng

interface GetCurrentLocation {
  fun getCurrentLocation(): Maybe<LatLng>
}