package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.repository.category.ObserveCategoriesRepository
import neuro.expenses.register.entity.mocks.categoryDtoMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class ObserveCategoriesUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun observeCategories() {
    val observeCategoriesRepository = mock<ObserveCategoriesRepository>()

    val observeCategoriesUseCase = ObserveCategoriesUseCaseImpl(observeCategoriesRepository)

    val expectedCategoryDtoList = listOf(categoryDtoMock())

    whenever(observeCategoriesRepository.observeCategories()).thenReturn(
      Observable.just(expectedCategoryDtoList)
        .observeSubscription()
    )

    observeCategoriesUseCase.observeCategories()
      .test()
      .assertValue(expectedCategoryDtoList)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
