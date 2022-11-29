package neuro.expenses.register.entity.mocks

import neuro.expenses.register.entity.PlaceProduct
import neuro.expenses.register.entity.Product

fun placeProductMock(id: Long = 1L, product: Product = productMock(id)): PlaceProduct {
  return PlaceProduct(id, product, categoryMock(), 1.0)
}

fun placeProductsMock(): List<PlaceProduct> {
  return listOf(placeProductMock(1), placeProductMock(2), placeProductMock(3))
}
