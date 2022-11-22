package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.place.GeneratePlaceIdRepository
import neuro.expenses.register.entity.controller.place.GeneratePlaceId

class GeneratePlaceIdImpl(private val generatePlaceIdRepository: GeneratePlaceIdRepository) :
  GeneratePlaceId {
  override fun newId(): Single<Long> {
    return generatePlaceIdRepository.newId()
  }
}