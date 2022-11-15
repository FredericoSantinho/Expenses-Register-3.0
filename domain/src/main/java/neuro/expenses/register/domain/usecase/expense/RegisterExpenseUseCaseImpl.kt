package neuro.expenses.register.domain.usecase.expense

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.mapper.ExpenseMapper
import neuro.expenses.register.domain.mapper.RegisterExpenseErrorMapper
import neuro.expenses.register.entity.controller.expense.RegisterExpense

class RegisterExpenseUseCaseImpl(
  private val registerExpense: RegisterExpense,
  private val expenseMapper: ExpenseMapper,
  private val expenseErrorMapper: RegisterExpenseErrorMapper
) :
  RegisterExpenseUseCase {
  override fun registerExpense(expenseDto: ExpenseDto): Completable {
    return registerExpense.registerExpense(expenseMapper.map(expenseDto)).onErrorResumeNext {
      if (it is neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseException) {
        Completable.error(RegisterExpenseException(expenseErrorMapper.map(it.errors)))
      } else {
        Completable.error(it)
      }
    }
  }
}