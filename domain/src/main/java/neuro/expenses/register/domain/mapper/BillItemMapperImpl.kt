package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.entity.BillItem
import java.util.*

class BillItemMapperImpl(private val productMapper: ProductMapper) : BillItemMapper {
  override fun map(billItem: BillItem, calendar: Calendar): BillItemDto {
    return BillItemDto(
      billItem.id,
      productMapper.map(billItem.product),
      billItem.amount,
      billItem.total
    )
  }

  override fun map(billItemDto: BillItemDto): BillItem {
    return BillItem(
      billItemDto.id,
      productMapper.map(billItemDto.product),
      billItemDto.amount,
      billItemDto.total
    )
  }
}