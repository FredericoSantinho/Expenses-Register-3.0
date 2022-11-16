package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.entity.Expense

fun Expense.toDomain(): ExpenseDto =
  ExpenseDto(description, category, place, price, amount, calendar)

fun ExpenseDto.toEntity(): Expense = Expense(description, category, place, price, amount, calendar)
