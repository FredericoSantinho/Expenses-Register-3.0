package neuro.expenses.register.domain.entity.product

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.repository.product.SaveProductRepository
import neuro.expenses.register.entity.mocks.productDtoMock
import neuro.expenses.register.entity.test.mocks.productMock
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class SaveProductImplTest() : ObserveSubscriptionTest() {
  @Test
  fun saveProduct() {
    val saveProductRepository = mock<SaveProductRepository>()

    val saveProduct = SaveProductImpl(saveProductRepository)

    val productDto = productDtoMock()
    val product = productMock()

    whenever(saveProductRepository.saveProduct(productDto)).thenReturn(
      Completable.complete()
        .observeSubscription()
    )

    saveProduct.saveProduct(product)
      .test()
      .assertComplete()
      .assertNoErrors()

    assertSubscription()
  }
}
