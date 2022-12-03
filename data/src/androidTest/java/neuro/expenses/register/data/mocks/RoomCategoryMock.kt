package neuro.expenses.register.data.mocks

import neuro.expenses.register.data.model.category.RoomCategory
import neuro.test.rx.incrementer

fun roomCategoryMock(categoryId: Long= incrementer.getAndIncrement()): RoomCategory {
  return RoomCategory(categoryId, "cat $categoryId", "iconUrl")
}