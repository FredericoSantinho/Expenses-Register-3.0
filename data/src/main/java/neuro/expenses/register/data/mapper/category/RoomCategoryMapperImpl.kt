package neuro.expenses.register.data.mapper.category

import neuro.expenses.register.data.model.RoomCategory
import neuro.expenses.register.domain.dto.CategoryDto

class RoomCategoryMapperImpl : RoomCategoryMapper {
  override fun map(categories: List<RoomCategory>): List<CategoryDto> {
    return categories.map { map(it) }
  }

  override fun map(category: RoomCategory): CategoryDto {
    return CategoryDto(category.categoryId, category.name)
  }
}