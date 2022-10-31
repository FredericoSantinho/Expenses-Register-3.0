package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.data.dao.CategoryDao
import neuro.expenses.register.data.mapper.CategoriesMapper
import neuro.expenses.register.domain.repository.GetCategoriesRepository

class GetCategoriesRepositoryImpl(
  private val categoryDao: CategoryDao,
  private val categoriesMapper: CategoriesMapper
) : GetCategoriesRepository {
  override fun getCategories(): Observable<List<String>> {
    return categoryDao.observeCategories().map { categoriesMapper.map(it) }
  }
}