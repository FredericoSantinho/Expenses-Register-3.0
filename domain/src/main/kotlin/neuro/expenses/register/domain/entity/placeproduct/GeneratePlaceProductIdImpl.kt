package neuro.expenses.register.domain.entity.placeproduct

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.placeproduct.GeneratePlaceProductIdRepository
import neuro.expenses.register.entity.placeproduct.GeneratePlaceProductId

class GeneratePlaceProductIdImpl(private val generatePlaceProductIdRepository: GeneratePlaceProductIdRepository) :
  GeneratePlaceProductId {
  override fun newId(): Single<Long> {
    return generatePlaceProductIdRepository.newId()
  }
}