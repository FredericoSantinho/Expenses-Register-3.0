package neuro.expenses.register.domain.usecase.expense

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.mapper.toDomain
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.entity.controller.expense.RegisterExpense

class RegisterExpenseUseCaseImpl(private val registerExpense: RegisterExpense) :
  RegisterExpenseUseCase {
  override fun registerExpense(expenseDto: ExpenseDto): Completable {
    return registerExpense.registerExpense(expenseDto.toEntity()).onErrorResumeNext {
      if (it is neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseException) {
        Completable.error(RegisterExpenseException(it.errors.toDomain()))
      } else {
        Completable.error(it)
      }
    }
  }
}