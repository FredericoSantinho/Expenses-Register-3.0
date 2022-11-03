package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.entity.Bill
import java.util.*

class BillMapperImpl(
  private val billItemMapper: BillItemMapper
) : BillMapper {
  override fun map(bill: Bill): BillDto {
    val calendar = Calendar.getInstance()
    calendar.time = Date(bill.timestamp)
    val billItems = bill.billItems.map { billItemMapper.map(it, bill.place, calendar) }

    return BillDto(
      bill.id,
      bill.place,
      bill.timestamp,
      bill.total,
      billItems,
      bill.isOpen,
      bill.iconUrl
    )
  }

  override fun map(billDto: BillDto): Bill {
    val billItems = billDto.billItems.map { billItemMapper.map(it) }

    return Bill(
      billDto.id,
      billDto.place,
      billDto.timestamp,
      billDto.total,
      billItems,
      billDto.isOpen,
      billDto.iconUrl
    )
  }
}