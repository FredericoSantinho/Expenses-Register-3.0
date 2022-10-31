package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.entity.Expense

interface ExpenseMapper {
  fun map(expenseDto: ExpenseDto): Expense
  fun map(expense: Expense): ExpenseDto
}