package neuro.expenses.register.entity.controller.location

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.LatLng

interface GetCurrentLocation {
  fun getCurrentLocation(): Single<LatLng>
}