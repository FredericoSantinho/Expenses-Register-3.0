package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.entity.Bill

class BillMapperImpl(
  private val billItemMapper: BillItemMapper
) : BillMapper {
  override fun map(bill: Bill): BillDto {
    val billItems = bill.billItems.map { billItemMapper.map(it, bill.place, bill.calendar) }

    return BillDto(
      bill.id,
      bill.place,
      bill.calendar,
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
      billDto.calendar,
      billDto.total,
      billItems,
      billDto.isOpen,
      billDto.iconUrl
    )
  }
}