package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.dao.BillDao
import neuro.expenses.register.domain.repository.GenerateBillIdRepository

class GenerateBillIdRepositoryImpl(private val billDao: BillDao) : GenerateBillIdRepository {
  override fun newId(): Single<Long> {
    return billDao.getLastBillId().defaultIfEmpty(0).map { it + 1 }
  }
}