package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.repository.category.CreateCategoryRepository
import neuro.expenses.register.entity.category.GenerateCategoryId
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class CreateCategoryUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun createCategory() {
    val createCategoryRepository = mock<CreateCategoryRepository>()
    val generateCategoryId = mock<GenerateCategoryId>()

    val createCategoryUseCase = CreateCategoryUseCaseImpl(
      createCategoryRepository,
      generateCategoryId
    )

    val id = 1L
    val name = "name"
    val iconUrl = "iconUrl"
    val categoryDto = CategoryDto(id, name, iconUrl)

    whenever(generateCategoryId.newId()).thenReturn(
      Single.just(1L)
        .observeSubscription()
    )
    whenever(createCategoryRepository.createCategory(categoryDto)).thenReturn(
      Completable.complete()
        .observeSubscription()
    )

    createCategoryUseCase.createCategory(name, iconUrl)
      .test()
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
