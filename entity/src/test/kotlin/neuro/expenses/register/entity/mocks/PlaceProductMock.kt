package neuro.expenses.register.entity.mocks

import neuro.expenses.register.entity.PlaceProduct

fun placeProductMock(id: Long = 1L): PlaceProduct {
  return PlaceProduct(id, productMock(id), categoryMock(), 1.0)
}

fun placeProductsMock(): List<PlaceProduct> {
  return listOf(placeProductMock(1), placeProductMock(2), placeProductMock(3))
}
