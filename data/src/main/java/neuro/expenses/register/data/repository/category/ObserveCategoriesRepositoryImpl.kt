package neuro.expenses.register.data.repository.category

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.data.dao.CategoryDao
import neuro.expenses.register.data.mapper.category.toDomain
import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.repository.category.ObserveCategoriesRepository

class ObserveCategoriesRepositoryImpl(private val categoryDao: CategoryDao) :
  ObserveCategoriesRepository {
  override fun observeCategories(): Observable<List<CategoryDto>> {
    return categoryDao.observeCategories().map { it.toDomain() }
  }
}