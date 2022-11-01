package neuro.expenses.register.data.mapper

import neuro.expenses.register.data.model.RoomCategory

class RoomCategoriesMapperImpl : RoomCategoriesMapper {
  override fun map(categories: List<RoomCategory>): List<String> {
    return categories.map { it.name }
  }
}