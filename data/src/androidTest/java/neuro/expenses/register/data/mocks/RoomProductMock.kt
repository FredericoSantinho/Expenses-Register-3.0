package neuro.expenses.register.data.mocks

import neuro.expenses.register.data.model.product.RoomProduct
import neuro.test.rx.incrementer

fun roomProductMock(productId: Long= incrementer.getAndIncrement()): RoomProduct {
  return RoomProduct(productId, "desc $productId", "iconUrl", false)
}