package neuro.expenses.register.domain.usecase.place

import neuro.expenses.register.entity.mocks.placeProductsDtoMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test

internal class SortPlaceProductsImplTest() : ObserveSubscriptionTest() {
  @Test
  fun sortPlaceProducts() {

    val sortPlaceProducts = SortPlaceProductsImpl()

    val placeProductsDtos = placeProductsDtoMock()
    val expectedPlaceProductDtoList = placeProductsDtoMock()

    sortPlaceProducts.sortPlaceProducts(placeProductsDtos)
      .test()
      .assertValue(expectedPlaceProductDtoList)
      .assertComplete()
      .assertNoErrors()
  }
}
