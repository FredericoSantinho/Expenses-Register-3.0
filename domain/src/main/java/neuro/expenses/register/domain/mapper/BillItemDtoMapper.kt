package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.domain.entity.BillItem

interface BillItemDtoMapper {
  fun map(billItem: BillItem): BillItemDto
  fun map(billItemDto: BillItemDto): BillItem
}