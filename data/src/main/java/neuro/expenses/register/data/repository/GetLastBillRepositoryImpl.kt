package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.data.dao.BillDao
import neuro.expenses.register.data.mapper.bill.RoomBillWithBillItemsMapper
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.repository.GetLastBillRepository

class GetLastBillRepositoryImpl(
  private val billDao: BillDao,
  private val roomBillWithBillItemsMapper: RoomBillWithBillItemsMapper
) : GetLastBillRepository {
  override fun getLastBill(): Maybe<BillDto> {
    return billDao.getLastBill().map { roomBillWithBillItemsMapper.map(it) }
  }
}