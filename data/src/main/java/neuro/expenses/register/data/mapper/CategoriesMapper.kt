package neuro.expenses.register.data.mapper

import neuro.expenses.register.data.model.RoomCategory

interface CategoriesMapper {
  fun map(categories: List<RoomCategory>): List<String>
}