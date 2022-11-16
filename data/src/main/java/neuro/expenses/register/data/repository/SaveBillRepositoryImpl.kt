package neuro.expenses.register.data.repository

import neuro.expenses.register.data.dao.BillDao
import neuro.expenses.register.data.mapper.bill.toData
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.repository.SaveBillRepository

class SaveBillRepositoryImpl(private val billDao: BillDao) : SaveBillRepository {
  override fun saveBill(billDto: BillDto) {
    val roomBill = billDto.toData()
    val roomBillItems = billDto.billItems.toData(billDto.id)

    billDao.insert(roomBill, roomBillItems)
  }
}