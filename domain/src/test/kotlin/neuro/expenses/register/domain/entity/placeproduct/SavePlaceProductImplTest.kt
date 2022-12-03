package neuro.expenses.register.domain.entity.placeproduct

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.repository.product.SavePlaceProductRepository
import neuro.expenses.register.entity.mocks.placeProductDtoMock
import neuro.expenses.register.entity.test.mocks.placeProductMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class SavePlaceProductImplTest() : ObserveSubscriptionTest() {
  @Test
  fun savePlaceProduct() {
    val savePlaceProductRepository = mock<SavePlaceProductRepository>()

    val savePlaceProduct = SavePlaceProductImpl(savePlaceProductRepository)

    val placeProductDto = placeProductDtoMock()
    val placeProduct = placeProductMock()

    whenever(savePlaceProductRepository.savePlaceProduct(placeProductDto)).thenReturn(
      Completable.complete()
        .observeSubscription()
    )

    savePlaceProduct.savePlaceProduct(placeProduct)
      .test()
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
