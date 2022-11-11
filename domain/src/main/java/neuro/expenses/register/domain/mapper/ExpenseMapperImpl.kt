package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.entity.Expense

class ExpenseMapperImpl : ExpenseMapper {
  override fun map(expenseDto: ExpenseDto): Expense {
    return Expense(
      expenseDto.description,
      expenseDto.category,
      expenseDto.place,
      expenseDto.price,
      expenseDto.amount,
      expenseDto.calendar
    )
  }

  override fun map(expense: Expense): ExpenseDto {
    return ExpenseDto(
      expense.description,
      expense.category,
      expense.place,
      expense.price,
      expense.amount,
      expense.calendar
    )
  }
}