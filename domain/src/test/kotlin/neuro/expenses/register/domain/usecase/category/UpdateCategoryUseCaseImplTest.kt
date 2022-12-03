package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.repository.category.UpdateCategoryRepository
import neuro.expenses.register.entity.mocks.categoryDtoMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class UpdateCategoryUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun updateCategory() {
    val updateCategoryRepository = mock<UpdateCategoryRepository>()

    val updateCategoryUseCase = UpdateCategoryUseCaseImpl(updateCategoryRepository)

    val categoryDto = categoryDtoMock()

    whenever(updateCategoryRepository.updateCategory(categoryDto)).thenReturn(
      Completable.complete()
        .observeSubscription()
    )

    updateCategoryUseCase.updateCategory(categoryDto)
      .test()
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
