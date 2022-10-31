package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.entity.Bill

interface BillDtoMapper {
  fun map(bill: Bill): BillDto
  fun map(billDto: BillDto): Bill
}