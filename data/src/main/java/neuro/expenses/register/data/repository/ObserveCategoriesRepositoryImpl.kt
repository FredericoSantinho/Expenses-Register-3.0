package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.data.dao.CategoryDao
import neuro.expenses.register.data.mapper.RoomCategoriesMapper
import neuro.expenses.register.domain.repository.ObserveCategoriesRepository

class ObserveCategoriesRepositoryImpl(
  private val categoryDao: CategoryDao,
  private val roomCategoriesMapper: RoomCategoriesMapper
) : ObserveCategoriesRepository {
  override fun observeCategories(): Observable<List<String>> {
    return categoryDao.observeCategories().map { roomCategoriesMapper.map(it) }
  }
}