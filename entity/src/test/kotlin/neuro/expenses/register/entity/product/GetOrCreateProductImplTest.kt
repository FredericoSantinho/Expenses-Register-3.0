package neuro.expenses.register.entity.product

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.mocks.productMock
import neuro.expenses.register.entity.model.Product
import neuro.test.rx.Incrementer
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetOrCreateProductImplTest : ObserveSubscriptionTest() {
  @Test
  fun nonExistentProduct() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val getProduct = mock<GetProduct>()
    val generateProductId = mock<GenerateProductId>()
    val saveProduct = mock<SaveProduct>()

    val getOrCreateProduct = GetOrCreateProductImpl(getProduct, generateProductId, saveProduct)

    val description = "description"
    val variableAmount = false
    val iconUrl = "iconUrl"
    val product = productMock(1L, description, iconUrl)

    whenever(getProduct.getProduct(description)).thenReturn(
      Maybe.empty<Product>().observeSubscription(incrementer, offset)
    )
    whenever(generateProductId.newId()).thenReturn(
      Single.just(1L).observeSubscription(incrementer, offset)
    )
    whenever(saveProduct.saveProduct(product)).thenReturn(
      Completable.complete().observeSubscription(incrementer, offset)
    )

    getOrCreateProduct.getOrCreateProduct(description, variableAmount, iconUrl).test()
      .assertValue(product).assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }

  @Test
  fun existentProduct() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val getProduct = mock<GetProduct>()
    val generateProductId = mock<GenerateProductId>()
    val saveProduct = mock<SaveProduct>()

    val getOrCreateProduct = GetOrCreateProductImpl(getProduct, generateProductId, saveProduct)

    val description = "description"
    val variableAmount = false
    val iconUrl = "iconUrl"
    val product = productMock(1L, description, iconUrl)

    whenever(getProduct.getProduct(description)).thenReturn(
      Maybe.just(product).observeSubscription(incrementer, offset)
    )
    whenever(generateProductId.newId()).thenReturn(Single.error(IllegalStateException()))

    getOrCreateProduct.getOrCreateProduct(description, variableAmount, iconUrl).test()
      .assertValue(product).assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }
}