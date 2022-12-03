package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.repository.category.SaveCategoryRepository
import neuro.expenses.register.entity.mocks.categoryDtoMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class SaveCategoryUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun saveCategory() {
    val saveCategoryRepository = mock<SaveCategoryRepository>()

    val saveCategoryUseCase = SaveCategoryUseCaseImpl(saveCategoryRepository)

    val categoryDto = categoryDtoMock()

    whenever(saveCategoryRepository.saveCategory(categoryDto)).thenReturn(
      Completable.complete()
        .observeSubscription()
    )

    saveCategoryUseCase.saveCategory(categoryDto)
      .test()
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }

  @Test
  fun saveCategories() {
    val saveCategoryRepository = mock<SaveCategoryRepository>()

    val saveCategoryUseCase = SaveCategoryUseCaseImpl(saveCategoryRepository)

    val categoryDto = categoryDtoMock()
    val categoriesDtos = listOf(categoryDto)

    whenever(saveCategoryRepository.saveCategory(categoryDto)).thenReturn(
      Completable.complete()
        .observeSubscription()
    )

    saveCategoryUseCase.saveCategories(categoriesDtos)
      .test()
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
