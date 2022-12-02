package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.repository.category.ObserveCategoriesRepository

class ObserveCategoriesUseCaseImpl(private val observeCategoriesRepository: ObserveCategoriesRepository) :
  ObserveCategoriesUseCase {

  override fun observeCategories(): Observable<List<CategoryDto>> {
    return observeCategoriesRepository.observeCategories()
  }
}