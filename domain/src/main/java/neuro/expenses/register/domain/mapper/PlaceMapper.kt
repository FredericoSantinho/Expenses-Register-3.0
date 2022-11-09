package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.dto.PlaceDto

interface PlaceMapper {
  fun map(expenseDto: ExpenseDto): PlaceDto
}