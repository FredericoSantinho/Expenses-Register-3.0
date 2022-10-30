package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.domain.entity.BillItem
import java.util.*

interface BillItemDtoMapper {
  fun map(billItem: BillItem, place: String, calendar: Calendar): BillItemDto
  fun map(billItemDto: BillItemDto): BillItem
}