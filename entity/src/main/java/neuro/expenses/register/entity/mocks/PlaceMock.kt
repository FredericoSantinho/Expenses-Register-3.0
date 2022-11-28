package neuro.expenses.register.entity.mocks

import neuro.expenses.register.entity.Place

fun placeMock(id: Long = 1L): Place {
  return Place(id, "name$id", placeProductsMock(), latLngMock())
}