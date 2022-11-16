package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.entity.BillItem
import java.util.*

interface BillItemMapper {
  fun map(billItem: BillItem, calendar: Calendar): BillItemDto
  fun map(billItemDto: BillItemDto): BillItem
}