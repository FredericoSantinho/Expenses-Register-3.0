package neuro.expenses.register.data.repository.category

import android.database.sqlite.SQLiteConstraintException
import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.data.dao.CategoryDao
import neuro.expenses.register.data.mapper.category.toData
import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.repository.category.CreateCategoryRepository
import neuro.expenses.register.domain.usecase.category.CreateCategoryError

class CreateCategoryRepositoryImpl(private val categoryDao: CategoryDao) :
  CreateCategoryRepository {
  override fun createCategory(categoryDto: CategoryDto): Completable {
    return categoryDao.insert(categoryDto.toData()).ignoreElement().onErrorResumeNext {
      val error = if (it is SQLiteConstraintException) {
        CreateCategoryError()
      } else {
        it
      }
      Completable.error(error)
    }
  }
}