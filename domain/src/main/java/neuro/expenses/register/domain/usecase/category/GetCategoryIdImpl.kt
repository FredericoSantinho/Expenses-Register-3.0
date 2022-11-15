package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.GetCategoryRepository
import neuro.expenses.register.entity.controller.GetCategoryId

class GetCategoryIdImpl(private val getCategoryRepository: GetCategoryRepository) : GetCategoryId {
  override fun getCategoryId(nameLowercase: String): Single<Long> {
    return getCategoryRepository.getCategory(nameLowercase).map { it.id }.defaultIfEmpty(-1)
  }
}