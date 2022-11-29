package neuro.expenses.register.data.mocks

import neuro.expenses.register.data.model.RoomPlaceProduct
import neuro.expenses.register.data.model.RoomProduct

fun roomPlaceProductMock(placeProductId: Long= incrementer.getAndIncrement(), productId: Long, categoryId: Long): RoomPlaceProduct {
  return RoomPlaceProduct(placeProductId, productId, categoryId, 1.0)
}