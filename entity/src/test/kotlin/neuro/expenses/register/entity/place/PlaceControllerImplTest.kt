package neuro.expenses.register.entity.place

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.model.*
import neuro.expenses.register.entity.placeproduct.GetOrCreatePlaceProduct
import neuro.test.rx.Incrementer
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

internal class PlaceControllerImplTest : ObserveSubscriptionTest() {
  @Test
  fun addNonExistingProduct() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val getOrCreatePlaceProduct = mock<GetOrCreatePlaceProduct>()
    val addPlaceProduct = mock<AddPlaceProduct>()
    val removePlaceProduct = mock<RemovePlaceProduct>()

    val description = "description"
    val categoryName = "cat"
    val price = 1.0
    val variableAmount = false
    val iconUrl = ""
    val placeId: Long = 0
    val placeProductId: Long = 1

    val product = Product(0, description, variableAmount, iconUrl)
    val category = Category(0, categoryName, "")

    val residualPlaceProduct = PlaceProduct(2, product, category, 0.0)
    val placeProduct = PlaceProduct(placeProductId, product, category, price)
    val place = Place(0, "", listOf(residualPlaceProduct), LatLng(0.0, 0.0))
    val newPrice = 1.2
    val placeWithProduct =
      Place(placeId, "", listOf(residualPlaceProduct, placeProduct), LatLng(0.0, 0.0))
    val placeController =
      PlaceControllerImpl(getOrCreatePlaceProduct, addPlaceProduct, removePlaceProduct)

    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(
        description, categoryName, newPrice, variableAmount, iconUrl
      )
    ).doReturn(Single.error(IllegalStateException()))
    whenever(addPlaceProduct.addPlaceProduct(placeId, placeProductId)).doReturn(
      Completable.complete().observeSubscription(incrementer, offset)
    )

    verifyNoInteractions(addPlaceProduct)

    placeController.addPlaceProduct(place, placeProduct).test().assertValue(placeWithProduct)
      .assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }

  @Test
  fun addExistingProduct() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val getOrCreatePlaceProduct = mock<GetOrCreatePlaceProduct>()
    val addPlaceProduct = mock<AddPlaceProduct>()
    val removePlaceProduct = mock<RemovePlaceProduct>()

    val description = "description"
    val categoryName = "cat"
    val price = 1.0
    val variableAmount = false
    val iconUrl = ""
    val placeId: Long = 0
    val placeProductId: Long = 1

    val product = Product(0, description, variableAmount, iconUrl)
    val category = Category(0, categoryName, "")

    val residualPlaceProduct = PlaceProduct(2, product, category, 0.0)
    val placeProduct = PlaceProduct(placeProductId, product, category, price)
    val newPrice = 1.2
    val newUpdatedPlaceProduct = PlaceProduct(placeProductId + 1, product, category, newPrice)
    val placeWithProduct =
      Place(placeId, "", listOf(residualPlaceProduct, placeProduct), LatLng(0.0, 0.0))
    val placeController =
      PlaceControllerImpl(getOrCreatePlaceProduct, addPlaceProduct, removePlaceProduct)

    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(
        description, categoryName, newPrice, variableAmount, iconUrl
      )
    ).doReturn(
      Single.just(newUpdatedPlaceProduct).observeSubscription(incrementer, offset)
    )
    whenever(
      addPlaceProduct.addPlaceProduct(
        placeId,
        placeProductId
      )
    ).doReturn(Completable.complete().observeSubscription(incrementer, offset))

    placeController.addPlaceProduct(placeWithProduct, placeProduct).test()
      .assertValue(placeWithProduct).assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }

  @Test
  fun removeNonExistentProduct() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val getOrCreatePlaceProduct = mock<GetOrCreatePlaceProduct>()
    val addPlaceProduct = mock<AddPlaceProduct>()
    val removePlaceProduct = mock<RemovePlaceProduct>()

    val description = "description"
    val categoryName = "cat"
    val price = 1.0
    val variableAmount = false
    val iconUrl = ""
    val placeId: Long = 0
    val placeProductId: Long = 1

    val product = Product(0, description, variableAmount, iconUrl)
    val category = Category(0, categoryName, "")

    val residualPlaceProduct = PlaceProduct(2, product, category, 0.0)
    val placeProduct = PlaceProduct(placeProductId, product, category, price)
    val newPrice = 1.2
    val placeWithProduct =
      Place(placeId, "", listOf(residualPlaceProduct, placeProduct), LatLng(0.0, 0.0))
    val placeController =
      PlaceControllerImpl(getOrCreatePlaceProduct, addPlaceProduct, removePlaceProduct)

    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(
        description, categoryName, newPrice, variableAmount, iconUrl
      )
    ).doReturn(Single.error(IllegalStateException()))
    whenever(addPlaceProduct.addPlaceProduct(placeId, placeProductId)).doReturn(
      Completable.complete().observeSubscription(incrementer, offset)
    )
    whenever(removePlaceProduct.removePlaceProduct(placeId, -1L)).doReturn(
      Completable.complete().observeSubscription(incrementer, offset)
    )

    placeController.removePlaceProduct(placeWithProduct, -1L).test().assertValue(placeWithProduct)
      .assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }

  @Test
  fun removeExistentProduct() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val getOrCreatePlaceProduct = mock<GetOrCreatePlaceProduct>()
    val addPlaceProduct = mock<AddPlaceProduct>()
    val removePlaceProduct = mock<RemovePlaceProduct>()

    val description = "description"
    val categoryName = "cat"
    val price = 1.0
    val variableAmount = false
    val iconUrl = ""
    val placeId: Long = 0
    val placeProductId: Long = 1

    val product = Product(0, description, variableAmount, iconUrl)
    val category = Category(0, categoryName, "")

    val residualPlaceProduct = PlaceProduct(2, product, category, 0.0)
    val placeProduct = PlaceProduct(placeProductId, product, category, price)
    val place = Place(0, "", listOf(residualPlaceProduct), LatLng(0.0, 0.0))
    val newPrice = 1.2
    val placeWithProduct =
      Place(placeId, "", listOf(residualPlaceProduct, placeProduct), LatLng(0.0, 0.0))
    val placeController =
      PlaceControllerImpl(getOrCreatePlaceProduct, addPlaceProduct, removePlaceProduct)

    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(
        description, categoryName, newPrice, variableAmount, iconUrl
      )
    ).doReturn(
      Single.error(IllegalStateException())
    )
    whenever(addPlaceProduct.addPlaceProduct(placeId, placeProductId)).doReturn(
      Completable.complete().observeSubscription(incrementer, offset)
    )
    whenever(removePlaceProduct.removePlaceProduct(placeId, placeProductId)).doReturn(
      Completable.complete().observeSubscription(incrementer, offset)
    )

    placeController.removePlaceProduct(placeWithProduct, placeProductId).test().assertValue(place)
      .assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }

  @Test
  fun updateProduct() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val getOrCreatePlaceProduct = mock<GetOrCreatePlaceProduct>()
    val addPlaceProduct = mock<AddPlaceProduct>()
    val removePlaceProduct = mock<RemovePlaceProduct>()

    val description = "description"
    val categoryName = "cat"
    val price = 1.0
    val variableAmount = false
    val iconUrl = ""
    val placeId: Long = 0
    val placeProductId: Long = 1

    val product = Product(0, description, variableAmount, iconUrl)
    val category = Category(0, categoryName, "")

    val residualPlaceProduct = PlaceProduct(2, product, category, 0.0)
    val placeProduct = PlaceProduct(placeProductId, product, category, price)
    val newPrice = 1.2
    val updatedPlaceProduct = PlaceProduct(placeProductId, product, category, newPrice)
    val newUpdatedPlaceProduct = PlaceProduct(placeProductId + 1, product, category, newPrice)
    val placeWithProduct =
      Place(placeId, "", listOf(residualPlaceProduct, placeProduct), LatLng(0.0, 0.0))
    val placeWithUpdatedProduct =
      Place(placeId, "", listOf(residualPlaceProduct, newUpdatedPlaceProduct), LatLng(0.0, 0.0))
    val placeController =
      PlaceControllerImpl(getOrCreatePlaceProduct, addPlaceProduct, removePlaceProduct)

    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(
        description, categoryName, newPrice, variableAmount, iconUrl
      )
    ).doReturn(
      Single.just(newUpdatedPlaceProduct).observeSubscription(incrementer, offset)
    )
    whenever(removePlaceProduct.removePlaceProduct(placeId, placeProductId)).doReturn(
      Completable.complete().observeSubscription(incrementer, offset)
    )
    whenever(addPlaceProduct.addPlaceProduct(placeId, newUpdatedPlaceProduct.id)).doReturn(
      Completable.complete().observeSubscription(incrementer, offset)
    )

    placeController.updatePlaceProduct(placeWithProduct, updatedPlaceProduct).test()
      .assertValue(placeWithUpdatedProduct).assertNoErrors().assertComplete()

    assertSubscriptions(incrementer.getAll(), offset)
  }
}