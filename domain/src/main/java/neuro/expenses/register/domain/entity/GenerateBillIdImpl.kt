package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.GenerateBillIdRepository
import neuro.expenses.register.entity.controller.bill.GenerateBillId

class GenerateBillIdImpl(private val generateBillIdRepository: GenerateBillIdRepository) :
  GenerateBillId {
  override fun newId(): Single<Long> {
    return generateBillIdRepository.newId()
  }
}