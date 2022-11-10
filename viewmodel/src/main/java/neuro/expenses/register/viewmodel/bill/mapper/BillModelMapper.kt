package neuro.expenses.register.viewmodel.bill.mapper

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.viewmodel.bill.model.BillModel

interface BillModelMapper {
  fun map(billDto: BillDto): BillModel
}