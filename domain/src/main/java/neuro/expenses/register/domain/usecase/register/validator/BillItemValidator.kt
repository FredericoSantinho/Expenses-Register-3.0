package neuro.expenses.register.domain.usecase.register.validator

import neuro.expenses.register.domain.dto.BillItemDto

interface BillItemValidator {
  fun validate(billItemDto: BillItemDto): List<RegisterExpenseError>
}