package neuro.expenses.register.entity.mocks

import neuro.expenses.register.entity.model.Place
import neuro.expenses.register.entity.model.PlaceProduct

fun placeMock(
  id: Long = 1L,
  placeName: String = "name $id",
  placeProducts: List<PlaceProduct> = placeProductsMock()
): Place {
  return Place(id, placeName, placeProducts, latLngMock())
}