package neuro.expenses.register.domain.usecase.populate

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.usecase.category.SaveCategoryUseCase
import neuro.expenses.register.domain.usecase.place.SavePlaceUseCase
import neuro.expenses.register.domain.usecase.product.GetOrCreatePlaceProductUseCase
import neuro.expenses.register.entity.mocks.placeProductDtoMock
import neuro.test.rx.Incrementer
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class PopulatePlacesImplTest() : ObserveSubscriptionTest() {
  @Test
  fun populatePlaces() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val saveCategoryUseCase = mock<SaveCategoryUseCase>()
    val getOrCreatePlaceProductUseCase = mock<GetOrCreatePlaceProductUseCase>()
    val savePlaceUseCase = mock<SavePlaceUseCase>()

    val populatePlaces = PopulatePlacesImpl(
      saveCategoryUseCase, getOrCreatePlaceProductUseCase, savePlaceUseCase
    )

    whenever(saveCategoryUseCase.saveCategories(any())).thenReturn(
      Completable.complete().observeSubscription(incrementer, offset)
    )
    whenever(
      getOrCreatePlaceProductUseCase.getOrCreatePlaceProduct(any(), any(), any(), any(), any())
    ).thenReturn(
      Single.just(
        placeProductDtoMock()
      ).observeSubscription(incrementer, offset)
    )
    whenever(savePlaceUseCase.savePlace(any())).thenReturn(
      Completable.complete().observeSubscription(incrementer, offset)
    )

    populatePlaces.populatePlaces().test().assertComplete().assertNoErrors()

    assertSubscriptions(incrementer.getAll(), offset)
  }
}
