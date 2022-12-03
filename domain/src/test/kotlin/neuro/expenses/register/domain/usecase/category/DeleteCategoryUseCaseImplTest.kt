package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.repository.category.DeleteCategoryRepository
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

internal class DeleteCategoryUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun deleteCategory() {
    val deleteCategoryRepository = mock<DeleteCategoryRepository>()

    val deleteCategoryUseCase = DeleteCategoryUseCaseImpl(deleteCategoryRepository)

    val categoryId = 1L

    whenever(deleteCategoryRepository.deleteCategory(categoryId)).thenReturn(
      Completable.complete()
        .observeSubscription()
    )

    deleteCategoryUseCase.deleteCategory(categoryId)
      .test()
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }

  @Test
  fun deleteCategoryError() {
    val deleteCategoryRepository = mock<DeleteCategoryRepository>()

    val deleteCategoryUseCase = DeleteCategoryUseCaseImpl(deleteCategoryRepository)

    val categoryId = 1L

    whenever(deleteCategoryRepository.deleteCategory(categoryId)).thenReturn(
      Completable.error(DeleteCategoryError())
        .observeSubscription()
    )

    deleteCategoryUseCase.deleteCategory(categoryId)
      .test()
      .assertNotComplete()
      .assertError(DeleteCategoryError())

    assertSubscription()

    assertEquals(DeleteCategoryError(), DeleteCategoryError())

    val map = mutableMapOf<Any, Boolean>()
    map.put(DeleteCategoryError(), true)

    assertEquals(1, map.values.size)

    map.put(DeleteCategoryError(), true)

    assertEquals(1, map.values.size)
  }
}
