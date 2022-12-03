package neuro.expenses.register.entity.test.mocks

import neuro.expenses.register.entity.model.LatLng

fun latLngMock(latitude: Double = 1.0, longitude: Double = 2.0): LatLng {
  return LatLng(latitude, longitude)
}