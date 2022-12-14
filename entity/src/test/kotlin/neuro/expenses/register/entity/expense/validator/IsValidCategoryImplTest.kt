package neuro.expenses.register.entity.expense.validator

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.category.GetCategory
import neuro.expenses.register.entity.model.Category
import neuro.expenses.register.entity.test.mocks.categoryMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class IsValidCategoryImplTest : ObserveSubscriptionTest() {
  @Test
  fun testValidCategory() {
    val getCategory = mock<GetCategory>()

    val isValidCategory = IsValidCategoryImpl(getCategory)

    whenever(getCategory.getCategory(any())).thenReturn(
      Maybe.just(categoryMock()).observeSubscription()
    )

    isValidCategory.isValidCategory("cat").test().assertValue(true).assertNoErrors()
      .assertComplete()

    assertSubscription()
  }

  @Test
  fun testInvalidCategory() {
    val getCategory = mock<GetCategory>()

    val isValidCategory = IsValidCategoryImpl(getCategory)

    whenever(getCategory.getCategory(any())).thenReturn(
      Maybe.empty<Category>().observeSubscription()
    )

    isValidCategory.isValidCategory("cat").test().assertValue(false).assertNoErrors()
      .assertComplete()

    assertSubscription()
  }
}