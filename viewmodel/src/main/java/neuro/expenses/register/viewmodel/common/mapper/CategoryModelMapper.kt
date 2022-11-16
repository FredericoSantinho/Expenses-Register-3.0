package neuro.expenses.register.viewmodel.common.mapper

import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.viewmodel.common.model.CategoryModel

fun CategoryDto.toViewmodel() = CategoryModel(id, name)