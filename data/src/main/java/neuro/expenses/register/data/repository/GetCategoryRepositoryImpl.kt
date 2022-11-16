package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.data.dao.CategoryDao
import neuro.expenses.register.data.mapper.category.toDomain
import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.repository.GetCategoryRepository

class GetCategoryRepositoryImpl(private val categoryDao: CategoryDao) : GetCategoryRepository {
  override fun getCategory(nameLowercase: String): Maybe<CategoryDto> {
    return categoryDao.getCategory(nameLowercase).map { it.toDomain() }
  }
}