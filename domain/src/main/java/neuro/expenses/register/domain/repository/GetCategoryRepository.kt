package neuro.expenses.register.domain.repository

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.CategoryDto

interface GetCategoryRepository {
  fun getCategory(nameLowercase: String): Maybe<CategoryDto>
}