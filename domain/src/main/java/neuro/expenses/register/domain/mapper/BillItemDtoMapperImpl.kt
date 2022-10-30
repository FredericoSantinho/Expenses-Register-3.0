package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.domain.entity.BillItem
import java.util.*

class BillItemDtoMapperImpl(private val productDtoMapper: ProductDtoMapper) : BillItemDtoMapper {
  override fun map(billItem: BillItem, place: String, calendar: Calendar): BillItemDto {
    return BillItemDto(productDtoMapper.map(billItem.product), place, billItem.amount, calendar)
  }

  override fun map(billItemDto: BillItemDto): BillItem {
    return BillItem(
      productDtoMapper.map(billItemDto.product),
      billItemDto.amount
    )
  }
}