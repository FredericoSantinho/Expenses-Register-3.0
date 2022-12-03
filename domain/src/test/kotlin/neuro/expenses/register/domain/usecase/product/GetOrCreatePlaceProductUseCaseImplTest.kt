package neuro.expenses.register.domain.usecase.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.mocks.placeProductDtoMock
import neuro.expenses.register.entity.placeproduct.GetOrCreatePlaceProduct
import neuro.expenses.register.entity.test.mocks.placeProductMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetOrCreatePlaceProductUseCaseImplTest() : ObserveSubscriptionTest() {
  @Test
  fun getOrCreatePlaceProduct() {
    val getOrCreatePlaceProduct = mock<GetOrCreatePlaceProduct>()

    val getOrCreatePlaceProductUseCase = GetOrCreatePlaceProductUseCaseImpl(getOrCreatePlaceProduct)

    val description = "description"
    val category = "category"
    val price = 1.0
    val variableAmount = false
    val iconUrl = "iconUrl"
    val placeProduct = placeProductMock()

    val expectedPlaceProductDto = placeProductDtoMock()

    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(
        description, category, price,
        variableAmount, iconUrl
      )
    ).thenReturn(
      Single.just(placeProduct)
        .observeSubscription()
    )

    getOrCreatePlaceProductUseCase.getOrCreatePlaceProduct(
      description, category, price,
      variableAmount, iconUrl
    )
      .test()
      .assertValue(expectedPlaceProductDto)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
