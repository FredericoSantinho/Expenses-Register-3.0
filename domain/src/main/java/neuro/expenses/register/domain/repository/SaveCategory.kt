package neuro.expenses.register.domain.repository

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.CategoryDto

interface SaveCategoryRepository {
  fun saveCategory(categoryDto: CategoryDto): Completable
}