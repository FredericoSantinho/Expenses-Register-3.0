package neuro.expenses.register.domain.entity.placeproduct

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.repository.placeproduct.GetPlaceProductRepository
import neuro.expenses.register.entity.mocks.placeProductDtoMock
import neuro.expenses.register.entity.test.mocks.placeProductMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetPlaceProductImplTest() : ObserveSubscriptionTest() {
  @Test
  fun getPlaceProduct() {
    val getPlaceProductRepository = mock<GetPlaceProductRepository>()

    val getPlaceProduct = GetPlaceProductImpl(getPlaceProductRepository)

    val description = "description"
    val category = "category"
    val price = 1.0

    val placeProductDto = placeProductDtoMock()
    val expectedPlaceProduct = placeProductMock()

    whenever(getPlaceProductRepository.getPlaceProduct(description, category, price)).thenReturn(
      Maybe.just(placeProductDto)
        .observeSubscription()
    )

    getPlaceProduct.getPlaceProduct(description, category, price)
      .test()
      .assertValue(expectedPlaceProduct)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
