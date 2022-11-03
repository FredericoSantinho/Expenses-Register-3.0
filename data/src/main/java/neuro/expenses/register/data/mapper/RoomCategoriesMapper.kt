package neuro.expenses.register.data.mapper

import neuro.expenses.register.data.model.RoomCategory

interface RoomCategoriesMapper {
  fun map(categories: List<RoomCategory>): List<String>
  fun map(category: RoomCategory): String
}