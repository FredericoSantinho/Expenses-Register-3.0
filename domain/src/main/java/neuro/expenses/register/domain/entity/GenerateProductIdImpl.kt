package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.GenerateProductIdRepository
import neuro.expenses.register.entity.controller.GenerateProductId

class GenerateProductIdImpl(private val generateProductIdRepository: GenerateProductIdRepository) :
  GenerateProductId {
  override fun newId(): Single<Long> {
    return generateProductIdRepository.newId()
  }
}