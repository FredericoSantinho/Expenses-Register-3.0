package neuro.expenses.register.domain.entity.bill

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.bill.GenerateBillItemIdRepository
import neuro.expenses.register.entity.bill.GenerateBillItemId

class GenerateBillItemIdImpl(private val generateBillItemIdRepository: GenerateBillItemIdRepository) :
  GenerateBillItemId {
  override fun newId(): Single<Long> {
    return generateBillItemIdRepository.newId()
  }
}