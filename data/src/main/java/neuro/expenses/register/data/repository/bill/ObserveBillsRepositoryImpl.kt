package neuro.expenses.register.data.repository.bill

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.data.dao.BillDao
import neuro.expenses.register.data.mapper.bill.toDomain
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.repository.bill.ObserveBillsRepository

class ObserveBillsRepositoryImpl(private val billDao: BillDao) : ObserveBillsRepository {
  override fun observeBills(): Observable<List<BillDto>> {
    return billDao.observeBills().map { it.toDomain() }
  }
}