package neuro.expenses.register.data.repository.bill

import neuro.expenses.register.data.dao.BillDao
import neuro.expenses.register.data.mapper.bill.toData
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.repository.bill.SaveBillRepository

class SaveBillRepositoryImpl(private val billDao: BillDao) : SaveBillRepository {
  override fun saveBill(billDto: BillDto) {
    val roomBill = billDto.toData()
    val roomBillItems = billDto.billItemsDtos.toData(billDto.id)

    billDao.insert(roomBill, roomBillItems)
  }
}