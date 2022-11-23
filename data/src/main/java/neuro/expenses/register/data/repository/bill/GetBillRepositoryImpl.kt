package neuro.expenses.register.data.repository.bill

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.dao.BillDao
import neuro.expenses.register.data.mapper.bill.toDomain
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.repository.bill.GetBillRepository

class GetBillRepositoryImpl(private val billDao: BillDao) : GetBillRepository {
  override fun getBill(id: Long): Single<BillDto> {
    return billDao.getBill(id).map { it.toDomain() }
  }
}