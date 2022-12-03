package neuro.expenses.register.entity.expense.validator

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.test.mocks.expenseMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class ExpenseValidatorImplTest : ObserveSubscriptionTest() {
  @Test
  fun validExpense() {
    val isValidCategory = mock<IsValidCategory>()
    val expenseValidator = ExpenseValidatorImpl(isValidCategory)

    whenever(isValidCategory.isValidCategory(any())).thenReturn(
      Single.just(true).observeSubscription()
    )

    expenseValidator.validate(expenseMock()).test().assertNoErrors().assertComplete()

    assertSubscription()
  }

  @Test
  fun invalidDescription() {
    val isValidCategory = mock<IsValidCategory>()
    val expenseValidator = ExpenseValidatorImpl(isValidCategory)

    whenever(isValidCategory.isValidCategory(any())).thenReturn(
      Single.just(true).observeSubscription()
    )

    expenseValidator.validate(expenseMock(description = "")).test()
      .assertError { isError(it, RegisterExpenseError.EMPTY_DESCRIPTION) }.assertNotComplete()

    assertSubscription()
  }

  @Test
  fun invalidPlace() {
    val isValidCategory = mock<IsValidCategory>()
    val expenseValidator = ExpenseValidatorImpl(isValidCategory)

    whenever(isValidCategory.isValidCategory(any())).thenReturn(
      Single.just(true).observeSubscription()
    )

    expenseValidator.validate(expenseMock(place = "")).test()
      .assertError { isError(it, RegisterExpenseError.EMPTY_PLACE) }.assertNotComplete()

    assertSubscription()
  }

  @Test
  fun invalidAmount() {
    val isValidCategory = mock<IsValidCategory>()
    val expenseValidator = ExpenseValidatorImpl(isValidCategory)

    whenever(isValidCategory.isValidCategory(any())).thenReturn(
      Single.just(true).observeSubscription()
    )

    expenseValidator.validate(expenseMock(amount = -0.01)).test()
      .assertError { isError(it, RegisterExpenseError.INVALID_AMOUNT) }.assertNotComplete()

    assertSubscription()
  }

  @Test
  fun invalidCategory() {
    val isValidCategory = mock<IsValidCategory>()
    val expenseValidator = ExpenseValidatorImpl(isValidCategory)

    whenever(isValidCategory.isValidCategory(any())).thenReturn(
      Single.just(false).observeSubscription()
    )

    expenseValidator.validate(expenseMock()).test()
      .assertError { isError(it, RegisterExpenseError.CATEGORY_NOT_EXISTS) }.assertNotComplete()

    assertSubscription()
  }

  @Test
  fun invalidAll() {
    val isValidCategory = mock<IsValidCategory>()
    val expenseValidator = ExpenseValidatorImpl(isValidCategory)

    whenever(isValidCategory.isValidCategory(any())).thenReturn(
      Single.just(false).observeSubscription()
    )

    expenseValidator.validate(expenseMock(description = "", place = "", amount = -0.01)).test()
      .assertError {
        isError(
          it, listOf(
            RegisterExpenseError.EMPTY_DESCRIPTION,
            RegisterExpenseError.EMPTY_PLACE,
            RegisterExpenseError.INVALID_AMOUNT,
            RegisterExpenseError.CATEGORY_NOT_EXISTS
          )
        )
      }.assertNotComplete()

    assertSubscription()
  }

  private fun isError(throwable: Throwable, registerExpenseError: RegisterExpenseError) =
    throwable is RegisterExpenseException && throwable.errors == listOf(registerExpenseError)

  private fun isError(throwable: Throwable, registerExpenseErrors: List<RegisterExpenseError>) =
    throwable is RegisterExpenseException && throwable.errors == registerExpenseErrors
}