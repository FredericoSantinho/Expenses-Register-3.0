package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.GenerateBillItemIdRepository
import neuro.expenses.register.entity.controller.bill.GenerateBillItemId

class GenerateBillItemIdImpl(private val generateBillItemIdRepository: GenerateBillItemIdRepository) :
  GenerateBillItemId {
  override fun newId(): Single<Long> {
    return generateBillItemIdRepository.newId()
  }
}