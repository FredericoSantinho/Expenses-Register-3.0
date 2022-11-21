package neuro.expenses.register.viewmodel.mapper

import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.viewmodel.model.CategoryModel

fun CategoryDto.toViewmodel(): CategoryModel = CategoryModel(id, name)