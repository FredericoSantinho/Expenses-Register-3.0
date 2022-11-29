package neuro.expenses.register.entity.mocks

import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.PlaceProduct

fun placeMock(
  id: Long = 1L,
  placeName: String = "name $id",
  placeProducts: List<PlaceProduct> = placeProductsMock()
): Place {
  return Place(id, placeName, placeProducts, latLngMock())
}