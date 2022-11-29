package neuro.expenses.register.data.mocks

import neuro.expenses.register.data.model.RoomCategory
import neuro.test.incrementer

fun roomCategoryMock(categoryId: Long= incrementer.getAndIncrement()): RoomCategory {
  return RoomCategory(categoryId, "cat $categoryId", "iconUrl")
}