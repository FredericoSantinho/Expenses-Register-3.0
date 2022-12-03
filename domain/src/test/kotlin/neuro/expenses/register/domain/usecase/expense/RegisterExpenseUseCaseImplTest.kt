package neuro.expenses.register.domain.usecase.expense

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.expense.RegisterExpense
import neuro.expenses.register.entity.mocks.expenseDtoMock
import neuro.expenses.register.entity.test.mocks.expenseMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class RegisterExpenseUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun registerExpense() {
    val registerExpense = mock<RegisterExpense>()

    val registerExpenseUseCase = RegisterExpenseUseCaseImpl(registerExpense)

    val expense = expenseMock()

    val expenseDto = expenseDtoMock()

    whenever(registerExpense.registerExpense(expense)).thenReturn(
      Completable.complete().observeSubscription()
    )

    registerExpenseUseCase.registerExpense(expenseDto).test().assertComplete().assertNoErrors()

    assertSubscription()
  }

  @Test
  fun registerExpenseWithErrors() {
    val registerExpense = mock<RegisterExpense>()

    val registerExpenseUseCase = RegisterExpenseUseCaseImpl(registerExpense)

    val expense = expenseMock()

    val expenseDto = expenseDtoMock()

    whenever(registerExpense.registerExpense(expense)).thenReturn(
      Completable.error(buildEntityError()).observeSubscription()
    )

    registerExpenseUseCase.registerExpense(expenseDto).test().assertNotComplete()
      .assertError(buildError())

    assertSubscription()
  }

  @Test
  fun registerExpenseWithUnexpectedError() {
    val registerExpense = mock<RegisterExpense>()

    val registerExpenseUseCase = RegisterExpenseUseCaseImpl(registerExpense)

    val expense = expenseMock()

    val expenseDto = expenseDtoMock()

    val illegalStateException = IllegalStateException()
    whenever(registerExpense.registerExpense(expense)).thenReturn(
      Completable.error(illegalStateException).observeSubscription()
    )

    registerExpenseUseCase.registerExpense(expenseDto).test().assertNotComplete()
      .assertError(illegalStateException)

    assertSubscription()
  }

  private fun buildEntityError(): neuro.expenses.register.entity.expense.validator.RegisterExpenseException {
    return neuro.expenses.register.entity.expense.validator.RegisterExpenseException(
      listOf(
        neuro.expenses.register.entity.expense.validator.RegisterExpenseError.EMPTY_DESCRIPTION,
        neuro.expenses.register.entity.expense.validator.RegisterExpenseError.EMPTY_PLACE,
        neuro.expenses.register.entity.expense.validator.RegisterExpenseError.CATEGORY_NOT_EXISTS
      )
    )
  }

  private fun buildError(): RegisterExpenseException {
    return RegisterExpenseException(
      listOf(
        RegisterExpenseError.EMPTY_DESCRIPTION,
        RegisterExpenseError.EMPTY_PLACE,
        RegisterExpenseError.CATEGORY_NOT_EXISTS
      )
    )
  }
}
