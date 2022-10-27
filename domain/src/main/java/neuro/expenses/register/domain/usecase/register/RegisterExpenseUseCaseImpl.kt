package neuro.expenses.register.domain.usecase.register

import neuro.expenses.register.domain.entity.BillItem
import java.util.*

class RegisterExpenseUseCaseImpl : RegisterExpenseUseCase {
  override fun registerExpense(billItem: BillItem, calendar: Calendar): List<RegisterExpenseError> {
    return emptyList()
  }
}