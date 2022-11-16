package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.model.bill.RoomBillItem
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.dto.BillItemDto

class RoomBillItemMapperImpl : RoomBillItemMapper {

  override fun map(billItemDto: BillItemDto, billId: Long, placeProductId: Long): RoomBillItem {
    val billItemId = billItemDto.id
    val amount = billItemDto.amount

    return RoomBillItem(billItemId, amount, placeProductId, billId)
  }

  override fun map(billDto: BillDto): List<RoomBillItem> {
    val billId = billDto.id
    return billDto.billItems.map { billItemDto ->
      val placeProductId = billItemDto.product.id

      map(billItemDto, billId, placeProductId)
    }
  }
}