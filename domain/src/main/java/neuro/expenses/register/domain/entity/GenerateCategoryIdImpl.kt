package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.category.GenerateCategoryIdRepository
import neuro.expenses.register.entity.controller.category.GenerateCategoryId

class GenerateCategoryIdImpl(private val generateCategoryIdRepository: GenerateCategoryIdRepository) :
  GenerateCategoryId {
  override fun newId(): Single<Long> {
    return generateCategoryIdRepository.newId()
  }
}