package neuro.expenses.register.data.mapper.category

import neuro.expenses.register.data.model.RoomCategory
import neuro.expenses.register.domain.dto.CategoryDto

interface RoomCategoryMapper {
  fun map(categories: List<RoomCategory>): List<CategoryDto>
  fun map(category: RoomCategory): CategoryDto
}