package neuro.expenses.register.domain.usecase.register

import neuro.expenses.register.domain.dto.BillItemDto
import java.util.*

class RegisterExpenseUseCaseImpl : RegisterExpenseUseCase {
  override fun registerExpense(
    billItemDto: BillItemDto,
    calendar: Calendar
  ): List<RegisterExpenseError> {
    return emptyList()
  }
}