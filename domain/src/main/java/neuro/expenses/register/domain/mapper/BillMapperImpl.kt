package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.entity.Bill

class BillMapperImpl(
  private val placeMapper: PlaceMapper,
  private val billItemMapper: BillItemMapper
) : BillMapper {
  override fun map(bill: Bill): BillDto {
    val billItems = bill.billItems.map { billItemMapper.map(it, bill.calendar) }

    return BillDto(
      bill.id,
      placeMapper.map(bill.place),
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
      billDto.calendar,
      placeMapper.map(billDto.place),
      billDto.total,
      billItems,
      billDto.iconUrl,
      billDto.isOpen
    )
  }
}