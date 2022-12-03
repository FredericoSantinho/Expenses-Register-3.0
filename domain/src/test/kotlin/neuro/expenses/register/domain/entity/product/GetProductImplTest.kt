package neuro.expenses.register.domain.entity.product

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.repository.product.GetProductRepository
import neuro.expenses.register.entity.mocks.productDtoMock
import neuro.expenses.register.entity.test.mocks.productMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetProductImplTest() : ObserveSubscriptionTest() {
  @Test
  fun getProduct() {
    val getProductRepository = mock<GetProductRepository>()

    val getProduct = GetProductImpl(getProductRepository)

    val description = "description"

    val productDto = productDtoMock()
    val expectedProduct = productMock()

    whenever(getProductRepository.getProduct(description)).thenReturn(
      Maybe.just(productDto)
        .observeSubscription()
    )

    getProduct.getProduct(description)
      .test()
      .assertValue(expectedProduct)
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
