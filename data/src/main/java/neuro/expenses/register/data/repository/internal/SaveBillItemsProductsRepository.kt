package neuro.expenses.register.data.repository.internal

import neuro.expenses.register.data.model.bill.RoomBillItem
import neuro.expenses.register.domain.dto.BillDto

interface SaveBillItemsProductsRepository {
  fun saveBillItemsProducts(billDto: BillDto): List<RoomBillItem>
}