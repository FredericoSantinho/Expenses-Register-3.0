package neuro.expenses.register.domain.usecase.populate

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseUseCase
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class PopulateExpensesImplTest : ObserveSubscriptionTest() {
  @Test
  fun buildFakeExpensesList() {
    val registerExpenseUseCase = mock<RegisterExpenseUseCase>()

    val populateExpenses = PopulateExpensesImpl(registerExpenseUseCase)

    whenever(registerExpenseUseCase.registerExpense(any())).thenReturn(
      Completable.complete().observeSubscription()
    )

    populateExpenses.populateExpenses()
      .test()
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
