package neuro.expenses.register.domain.entity.location

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.domain.service.location.GetCurrentLocationService
import neuro.expenses.register.entity.location.GetCurrentLocation
import neuro.expenses.register.entity.model.LatLng

class GetCurrentLocationImpl(private val getCurrentLocationService: GetCurrentLocationService) :
  GetCurrentLocation {
  override fun getCurrentLocation(): Single<LatLng> {
    return getCurrentLocationService.getCurrentLocation().map { it.toEntity() }
  }
}