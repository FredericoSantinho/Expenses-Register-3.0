package neuro.expenses.register.data.mocks

import neuro.expenses.register.data.model.place.LatLngModel
import neuro.expenses.register.data.model.place.RoomPlace

fun roomPlaceMock(placeId : Long= incrementer.getAndIncrement()): RoomPlace {
  return RoomPlace(placeId, "name $placeId", latLngModelMock())
}