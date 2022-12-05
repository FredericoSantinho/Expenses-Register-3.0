package neuro.expenses.register.data.repository.category

import android.database.sqlite.SQLiteConstraintException
import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.data.dao.CategoryDao
import neuro.expenses.register.data.mapper.category.toData
import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.repository.category.SaveCategoryRepository
import neuro.expenses.register.domain.usecase.category.SaveCategoryError

class SaveCategoryRepositoryImpl(private val categoryDao: CategoryDao) : SaveCategoryRepository {
  override fun saveCategory(categoryDto: CategoryDto): Completable {
    return categoryDao.insert(categoryDto.toData()).ignoreElement().onErrorResumeNext {
      val error = if (it is SQLiteConstraintException) {
        SaveCategoryError()
      } else {
        it
      }
      Completable.error(error)
    }
  }
}