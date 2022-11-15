package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.CategoryDto

interface GetCategoryUseCase {
  fun getCategory(name: String): Maybe<CategoryDto>
}