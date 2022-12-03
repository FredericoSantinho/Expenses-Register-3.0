package neuro.expenses.register.entity.test.mocks

import neuro.expenses.register.entity.model.LatLng
import neuro.expenses.register.entity.model.Place
import neuro.expenses.register.entity.model.PlaceProduct

fun placeMock(
  id: Long = 1L,
  placeName: String = "name $id",
  placeProducts: List<PlaceProduct> = placeProductsMock(),
  latLng: LatLng = latLngMock()
): Place {
  return Place(id, placeName, placeProducts, latLng)
}