package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.dao.BillDao
import neuro.expenses.register.domain.repository.GenerateBillItemIdRepository

class GenerateBillItemIdRepositoryImpl(private val billDao: BillDao) :
  GenerateBillItemIdRepository {
  override fun newId(): Single<Long> {
    return billDao.getLastBillItemId().defaultIfEmpty(0).map { it + 1 }
  }
}