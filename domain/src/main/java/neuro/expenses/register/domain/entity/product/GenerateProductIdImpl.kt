package neuro.expenses.register.domain.entity.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.product.GenerateProductIdRepository
import neuro.expenses.register.entity.product.GenerateProductId

class GenerateProductIdImpl(private val generateProductIdRepository: GenerateProductIdRepository) :
  GenerateProductId {
  override fun newId(): Single<Long> {
    return generateProductIdRepository.newId()
  }
}