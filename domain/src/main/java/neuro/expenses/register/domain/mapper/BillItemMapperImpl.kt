package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.domain.entity.BillItem
import java.util.*

class BillItemMapperImpl(private val productMapper: ProductMapper) : BillItemMapper {
  override fun map(billItem: BillItem, place: String, calendar: Calendar): BillItemDto {
    return BillItemDto(
      productMapper.map(billItem.product),
      billItem.amount,
      billItem.total
    )
  }

  override fun map(billItemDto: BillItemDto): BillItem {
    return BillItem(productMapper.map(billItemDto.product), billItemDto.amount, billItemDto.total)
  }
}