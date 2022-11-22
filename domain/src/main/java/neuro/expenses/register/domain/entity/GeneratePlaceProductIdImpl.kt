package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.product.GeneratePlaceProductIdRepository
import neuro.expenses.register.entity.controller.product.GeneratePlaceProductId

class GeneratePlaceProductIdImpl(private val generatePlaceProductIdRepository: GeneratePlaceProductIdRepository) :
  GeneratePlaceProductId {
  override fun newId(): Single<Long> {
    return generatePlaceProductIdRepository.newId()
  }
}