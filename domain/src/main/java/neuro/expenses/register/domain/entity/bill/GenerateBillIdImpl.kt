package neuro.expenses.register.domain.entity.bill

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.bill.GenerateBillIdRepository
import neuro.expenses.register.entity.bill.GenerateBillId

class GenerateBillIdImpl(private val generateBillIdRepository: GenerateBillIdRepository) :
  GenerateBillId {
  override fun newId(): Single<Long> {
    return generateBillIdRepository.newId()
  }
}