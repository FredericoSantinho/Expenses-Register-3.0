package neuro.expenses.register.data.mocks

import neuro.expenses.register.data.model.place.RoomPlace
import neuro.test.incrementer

fun roomPlaceMock(placeId : Long= incrementer.getAndIncrement()): RoomPlace {
  return RoomPlace(placeId, "name $placeId", latLngModelMock())
}