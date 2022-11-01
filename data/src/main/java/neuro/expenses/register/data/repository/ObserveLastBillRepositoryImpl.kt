package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.data.dao.BillDao
import neuro.expenses.register.data.mapper.bill.RoomBillWithBillItemsMapper
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.repository.ObserveLastBillRepository

class ObserveLastBillRepositoryImpl(
  private val billDao: BillDao,
  private val roomBillWithBillItemsMapper: RoomBillWithBillItemsMapper
) : ObserveLastBillRepository {
  override fun observeLastBill(): Observable<BillDto> {
    return billDao.observeLastBill().map { roomBillWithBillItemsMapper.map(it) }
  }
}