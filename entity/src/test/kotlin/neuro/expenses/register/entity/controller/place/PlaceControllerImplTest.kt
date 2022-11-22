package neuro.expenses.register.entity.controller.place

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.*
import neuro.expenses.register.entity.controller.product.GetOrCreatePlaceProduct
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class PlaceControllerImplTest {
  @Test
  fun test() {
    val getOrCreatePlaceProduct = mock<GetOrCreatePlaceProduct>()
    val addPlaceProduct = mock<AddPlaceProduct>()
    val removePlaceProduct = mock<RemovePlaceProduct>()


    val description = "desc"
    val categoryName = "cat"
    val price = 1.0
    val variableAmount = false
    val placeId: Long = 0
    val placeProductId: Long = 1

    val product = Product(0, description, variableAmount)
    val category = Category(0, categoryName)

    val residualPlaceProduct = PlaceProduct(2, product, category, 0.0)
    val placeProduct = PlaceProduct(placeProductId, product, category, price)
    val place = Place(0, "", listOf(residualPlaceProduct), LatLng(0.0, 0.0))
    val newPrice = 1.2
    val updatedPlaceProduct = PlaceProduct(placeProductId, product, category, newPrice)
    val placeWithProduct =
      Place(placeId, "", listOf(residualPlaceProduct, placeProduct), LatLng(0.0, 0.0))
    val placeWithUpdatedProduct =
      Place(placeId, "", listOf(residualPlaceProduct, updatedPlaceProduct), LatLng(0.0, 0.0))
    val placeController =
      PlaceControllerImpl(getOrCreatePlaceProduct, addPlaceProduct, removePlaceProduct)

    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(
        eq(description), eq(categoryName), eq(price), eq(variableAmount)
      )
    ).doReturn(
      Single.just(placeProduct)
    )
    whenever(
      getOrCreatePlaceProduct.getOrCreatePlaceProduct(
        eq(description), eq(categoryName), eq(newPrice), eq(variableAmount)
      )
    ).doReturn(
      Single.just(updatedPlaceProduct)
    )
    whenever(addPlaceProduct.addPlaceProduct(any(), any())).doReturn(Completable.complete())

    assertFalse { placeController.contains(place, placeProduct) }

    //region Add a product
    verify(getOrCreatePlaceProduct, times(0)).getOrCreatePlaceProduct(any(), any(), any(), any())
    verify(addPlaceProduct, times(0)).addPlaceProduct(any(), any())

    var newPlaceObservable = placeController.addPlaceProduct(place, placeProduct).test()
    var newPlace = newPlaceObservable.values().get(0)

    verify(getOrCreatePlaceProduct, times(1)).getOrCreatePlaceProduct(
      eq(description), eq(categoryName), eq(price), eq(variableAmount)
    )
    verify(addPlaceProduct, times(1)).addPlaceProduct(eq(placeId), eq(placeProductId))

    assertTrue { placeController.contains(newPlace, placeProduct) }
    newPlaceObservable.assertValue(placeWithProduct)
    //endregion

    //region Add a repeated product
    newPlaceObservable = placeController.addPlaceProduct(placeWithProduct, placeProduct).test()
    newPlace = newPlaceObservable.values().get(0)

    assertTrue { placeController.contains(newPlace, placeProduct) }
    newPlaceObservable.assertValue(placeWithProduct)
    //endregion

    //region Remove a product id that the place doesn't contain
    whenever(removePlaceProduct.removePlaceProduct(any(), any())).doReturn(Completable.complete())
    verify(removePlaceProduct, times(0)).removePlaceProduct(any(), any())

    newPlaceObservable = placeController.removePlaceProduct(newPlace, -1).test()
    newPlace = newPlaceObservable.values().get(0)

    verify(getOrCreatePlaceProduct, times(1)).getOrCreatePlaceProduct(
      eq(description), eq(categoryName), eq(price), eq(variableAmount)
    )
    verify(addPlaceProduct, times(1)).addPlaceProduct(eq(placeId), eq(placeProductId))
    verify(removePlaceProduct, times(1)).removePlaceProduct(eq(placeId), eq(-1))

    assertTrue { placeController.contains(newPlace, placeProduct) }
    newPlaceObservable.assertValue(placeWithProduct)
    //endregion


    //region Remove a product id that the place contains.
    newPlaceObservable = placeController.removePlaceProduct(newPlace, placeProductId).test()
    newPlace = newPlaceObservable.values().get(0)

    verify(getOrCreatePlaceProduct, times(1)).getOrCreatePlaceProduct(
      eq(description), eq(categoryName), eq(price), eq(variableAmount)
    )
    verify(addPlaceProduct, times(1)).addPlaceProduct(eq(placeId), eq(placeProductId))
    verify(removePlaceProduct, times(1)).removePlaceProduct(eq(placeId), eq(placeProductId))

    assertFalse { placeController.contains(newPlace, placeProduct) }
    newPlaceObservable.assertValue(place)
    //endregion

    //region Update a product
    newPlaceObservable =
      placeController.updatePlaceProduct(placeWithProduct, updatedPlaceProduct).test()
    newPlace = newPlaceObservable.values().get(0)

    verify(getOrCreatePlaceProduct, times(1)).getOrCreatePlaceProduct(
      eq(description), eq(categoryName), eq(price), eq(variableAmount)
    )
    verify(addPlaceProduct, times(2)).addPlaceProduct(eq(placeId), eq(placeProductId))
    verify(removePlaceProduct, times(2)).removePlaceProduct(eq(placeId), eq(placeProductId))

    assertFalse { placeController.contains(newPlace, placeProduct) }
    assertTrue { placeController.contains(newPlace, updatedPlaceProduct) }
    newPlaceObservable.assertValue(placeWithUpdatedProduct)
    //endregion
  }
}