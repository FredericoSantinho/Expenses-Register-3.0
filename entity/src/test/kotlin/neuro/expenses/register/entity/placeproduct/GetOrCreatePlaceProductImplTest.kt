package neuro.expenses.register.entity.placeproduct

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.category.GetCategory
import neuro.expenses.register.entity.mocks.categoryMock
import neuro.expenses.register.entity.mocks.placeProductMock
import neuro.expenses.register.entity.mocks.productMock
import neuro.expenses.register.entity.model.Category
import neuro.expenses.register.entity.model.PlaceProduct
import neuro.expenses.register.entity.product.GetOrCreateProduct
import neuro.test.rx.Incrementer
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetOrCreatePlaceProductImplTest : ObserveSubscriptionTest() {
  @Test
  fun nonExistentProduct() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val getPlaceProduct = mock<GetPlaceProduct>()
    val getOrCreateProduct = mock<GetOrCreateProduct>()
    val savePlaceProduct = mock<SavePlaceProduct>()
    val generatePlaceProductId = mock<GeneratePlaceProductId>()
    val getCategory = mock<GetCategory>()

    val getOrCreatePlaceProduct = GetOrCreatePlaceProductImpl(
      getPlaceProduct, getOrCreateProduct, savePlaceProduct, generatePlaceProductId, getCategory
    )

    val description = "description"
    val categoryName = "Category"
    val price = 1.0
    val variableAmount = false
    val iconUrl = "iconUrl"

    val product = productMock()
    val category = categoryMock(name = categoryName)
    val placeProduct = placeProductMock(category = category)

    whenever(getCategory.getCategory(categoryName.lowercase())).thenReturn(
      Maybe.just(category).observeSubscription(incrementer, offset)
    )
    whenever(
      getPlaceProduct.getPlaceProduct(
        description, categoryName, price
      )
    ).thenReturn(Maybe.just(placeProduct).observeSubscription(incrementer, offset))
    whenever(
      getOrCreateProduct.getOrCreateProduct(
        description.lowercase(), variableAmount, iconUrl
      )
    ).thenReturn(Single.just(product))

    getOrCreatePlaceProduct.getOrCreatePlaceProduct(
      description, categoryName, price, variableAmount, iconUrl
    ).test().assertValue(placeProduct).assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }

  @Test
  fun existentProduct() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val getPlaceProduct = mock<GetPlaceProduct>()
    val getOrCreateProduct = mock<GetOrCreateProduct>()
    val savePlaceProduct = mock<SavePlaceProduct>()
    val generatePlaceProductId = mock<GeneratePlaceProductId>()
    val getCategory = mock<GetCategory>()

    val getOrCreatePlaceProduct = GetOrCreatePlaceProductImpl(
      getPlaceProduct, getOrCreateProduct, savePlaceProduct, generatePlaceProductId, getCategory
    )

    val description = "description"
    val categoryName = "Category"
    val price = 1.0
    val variableAmount = false
    val iconUrl = "iconUrl"

    val product = productMock()
    val category = categoryMock(name = categoryName)
    val placeProduct = placeProductMock(category = category)

    whenever(getCategory.getCategory(categoryName.lowercase())).thenReturn(
      Maybe.just(category).observeSubscription(incrementer, offset)
    )
    whenever(
      getPlaceProduct.getPlaceProduct(
        description, categoryName, price
      )
    ).thenReturn(Maybe.empty<PlaceProduct>().observeSubscription(incrementer, offset))
    whenever(
      getOrCreateProduct.getOrCreateProduct(
        description.lowercase(), variableAmount, iconUrl
      )
    ).thenReturn(Single.just(product).observeSubscription(incrementer, offset))
    whenever(generatePlaceProductId.newId()).thenReturn(
      Single.just(1L).observeSubscription(incrementer, offset)
    )
    whenever(savePlaceProduct.savePlaceProduct(placeProduct)).thenReturn(
      Completable.complete().observeSubscription(incrementer, offset)
    )

    getOrCreatePlaceProduct.getOrCreatePlaceProduct(
      description, categoryName, price, variableAmount, iconUrl
    ).test().assertValue(placeProduct).assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }

  /**
   * Is supposed to fail on invalid category.
   */
  @Test
  fun invalidCategory() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val getPlaceProduct = mock<GetPlaceProduct>()
    val getOrCreateProduct = mock<GetOrCreateProduct>()
    val savePlaceProduct = mock<SavePlaceProduct>()
    val generatePlaceProductId = mock<GeneratePlaceProductId>()
    val getCategory = mock<GetCategory>()

    val getOrCreatePlaceProduct = GetOrCreatePlaceProductImpl(
      getPlaceProduct, getOrCreateProduct, savePlaceProduct, generatePlaceProductId, getCategory
    )

    val description = "description"
    val categoryName = "Category"
    val price = 1.0
    val variableAmount = false
    val iconUrl = "iconUrl"

    whenever(getCategory.getCategory(categoryName.lowercase())).thenReturn(
      Maybe.empty<Category>().observeSubscription(incrementer, offset)
    )

    getOrCreatePlaceProduct.getOrCreatePlaceProduct(
      description, categoryName, price, variableAmount, iconUrl
    ).test().assertNoValues().assertError(NoSuchElementException::class.java).assertNotComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }
}