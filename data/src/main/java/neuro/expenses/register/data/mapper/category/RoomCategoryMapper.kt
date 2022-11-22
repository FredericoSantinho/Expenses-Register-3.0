package neuro.expenses.register.data.mapper.category

import neuro.expenses.register.data.model.RoomCategory
import neuro.expenses.register.domain.dto.CategoryDto

fun RoomCategory.toDomain(): CategoryDto = CategoryDto(categoryId, name)

fun List<RoomCategory>.toDomain(): List<CategoryDto> = map { it.toDomain() }

fun CategoryDto.toData(): RoomCategory = RoomCategory(id, name)