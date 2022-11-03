package neuro.expenses.register.data.repository

import neuro.expenses.register.data.dao.BillDao
import neuro.expenses.register.data.mapper.bill.RoomBillMapper
import neuro.expenses.register.data.repository.internal.SaveBillItemsProductsRepository
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.repository.SaveBillRepository

class SaveBillRepositoryImpl(
  private val billDao: BillDao,
  private val roomBillMapper: RoomBillMapper,
  private val saveBillItemsProductsRepository: SaveBillItemsProductsRepository
) : SaveBillRepository {
  override fun saveBill(billDto: BillDto) {
    val roomBill = roomBillMapper.map(billDto)
    val roomBillItems = saveBillItemsProductsRepository.saveBillItemsProducts(billDto)

    billDao.insert(roomBill, roomBillItems)
  }
}