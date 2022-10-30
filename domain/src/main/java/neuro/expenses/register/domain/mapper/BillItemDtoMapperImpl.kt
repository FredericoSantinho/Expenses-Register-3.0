package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.domain.entity.BillItem

class BillItemDtoMapperImpl(private val productDtoMapper: ProductDtoMapper) : BillItemDtoMapper {
  override fun map(billItem: BillItem, place: String): BillItemDto {
    return BillItemDto(productDtoMapper.map(billItem.product), place, billItem.amount)
  }

  override fun map(billItemDto: BillItemDto): BillItem {
    return BillItem(
      productDtoMapper.map(billItemDto.product),
      billItemDto.amount
    )
  }
}