package neuro.expenses.register.domain.entity.category

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.repository.category.GetCategoryRepository
import neuro.expenses.register.entity.mocks.categoryDtoMock
import neuro.expenses.register.entity.test.mocks.categoryMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetCategoryImplTest() : ObserveSubscriptionTest() {
  @Test
  fun getCategory() {
    val getCategoryRepository = mock<GetCategoryRepository>()

    val getCategory = GetCategoryImpl(getCategoryRepository)

    val nameLowercase = String()

    val categoryDto = categoryDtoMock()
    val expectedCategory = categoryMock()

    whenever(getCategoryRepository.getCategory(nameLowercase)).thenReturn(
      Maybe.just(categoryDto)
        .observeSubscription()
    )

    getCategory.getCategory(nameLowercase)
      .test()
      .assertValue(expectedCategory)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
