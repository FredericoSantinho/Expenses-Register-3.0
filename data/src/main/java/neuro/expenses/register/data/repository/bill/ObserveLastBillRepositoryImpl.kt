package neuro.expenses.register.data.repository.bill

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.data.dao.BillDao
import neuro.expenses.register.data.mapper.bill.toDomain
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.repository.bill.ObserveLastBillRepository

class ObserveLastBillRepositoryImpl(private val billDao: BillDao) : ObserveLastBillRepository {
  override fun observeLastBill(): Observable<BillDto> {
    return billDao.observeLastBill().distinctUntilChanged().map { it.toDomain() }
  }
}