package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.CategoryDto

interface UpdateCategoryUseCase {
  fun updateCategory(categoryDto: CategoryDto): Completable
}