package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.domain.service.GetCurrentLocationService
import neuro.expenses.register.entity.LatLng
import neuro.expenses.register.entity.controller.location.GetCurrentLocation

class GetCurrentLocationImpl(private val getCurrentLocationService: GetCurrentLocationService) :
  GetCurrentLocation {
  override fun getCurrentLocation(): Maybe<LatLng> {
    return getCurrentLocationService.getCurrentLocation().map { it.toEntity() }
  }
}