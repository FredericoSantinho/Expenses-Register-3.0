package neuro.expenses.register.data.mocks

import neuro.expenses.register.data.model.RoomProduct
import neuro.test.incrementer

fun roomProductMock(productId: Long= incrementer.getAndIncrement()): RoomProduct {
  return RoomProduct(productId, "desc $productId", "iconUrl", false)
}