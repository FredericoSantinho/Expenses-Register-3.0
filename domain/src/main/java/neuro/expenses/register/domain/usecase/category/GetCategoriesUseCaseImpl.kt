package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.repository.GetCategoriesRepository

class GetCategoriesUseCaseImpl(private val getCategoriesRepository: GetCategoriesRepository) :
  GetCategoriesUseCase {

  override fun getCategories(): Observable<List<String>> {
    return getCategoriesRepository.getCategories()
  }
}