package neuro.expenses.register.ui.common.bill.mapper

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.ui.common.bill.model.BillModel

interface BillModelMapper {
  fun map(billDto: BillDto): BillModel
}