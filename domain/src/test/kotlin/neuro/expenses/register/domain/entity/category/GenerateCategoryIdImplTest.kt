package neuro.expenses.register.domain.entity.category

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.category.GenerateCategoryIdRepository
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

internal class GenerateCategoryIdImplTest : ObserveSubscriptionTest() {
  @Test
  fun generateCategoryId() {
    val generateCategoryIdRepository = mock<GenerateCategoryIdRepository>()
    val generateCategoryId = GenerateCategoryIdImpl(generateCategoryIdRepository)

    whenever(generateCategoryIdRepository.newId()).thenReturn(Single.just(1L).observeSubscription())

    verifyNoInteractions(generateCategoryIdRepository)
    generateCategoryId.newId().test().assertValue(1L)
    assertSubscription()
  }
}