package neuro.expenses.register

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.data.ExpensesRegisterDatabase
import neuro.expenses.register.data.model.RoomCategory
import neuro.expenses.register.data.model.place.LatLng
import neuro.expenses.register.data.model.place.PlacePricedProductCrossRef
import neuro.expenses.register.data.model.place.RoomPlace

class PrePopulateDatabase(
  private val expensesRegisterDatabase: ExpensesRegisterDatabase
) {
  fun prePopulateDatabase(): Completable {
    return Completable.fromAction {
      val categoryDao = expensesRegisterDatabase.categoryDao

      categoryDao.insert(
        listOf(
          RoomCategory("Café"),
          RoomCategory("Borga"),
          RoomCategory("Restau")
        )
      ).blockingGet()

      val placeDao = expensesRegisterDatabase.placeDao
      val placeName = "Bitoque"

      placeDao.insert(RoomPlace(placeName, LatLng(37.091495, -8.2475677))).blockingGet()

      var pricedProductId: Long
      pricedProductId =
        expensesRegisterDatabase.productDao.insert("Sagres Média 0,33cl", "Borga", 1.3)
      expensesRegisterDatabase.placeDao.insert(
        PlacePricedProductCrossRef(
          placeName,
          pricedProductId
        )
      ).blockingGet()

      pricedProductId =
        expensesRegisterDatabase.productDao.insert("Super Bock Média 0,33cl", "Borga", 1.3)
      expensesRegisterDatabase.placeDao.insert(
        PlacePricedProductCrossRef(
          placeName,
          pricedProductId
        )
      ).blockingGet()

      pricedProductId =
        expensesRegisterDatabase.productDao.insert("Mini Cristal 0,20cl", "Borga", 1.1)
      expensesRegisterDatabase.placeDao.insert(
        PlacePricedProductCrossRef(
          placeName,
          pricedProductId
        )
      ).blockingGet()

      pricedProductId =
        expensesRegisterDatabase.productDao.insert("Mini Sagres 0,25cl", "Borga", 1.1)
      expensesRegisterDatabase.placeDao.insert(
        PlacePricedProductCrossRef(
          placeName,
          pricedProductId
        )
      ).blockingGet()

      pricedProductId = expensesRegisterDatabase.productDao.insert("Chicharricos", "Restau", 1.5)
      expensesRegisterDatabase.placeDao.insert(
        PlacePricedProductCrossRef(
          placeName,
          pricedProductId
        )
      ).blockingGet()

      pricedProductId = expensesRegisterDatabase.productDao.insert("Twix 50g", "Restau", 1.5)
      expensesRegisterDatabase.placeDao.insert(
        PlacePricedProductCrossRef(
          placeName,
          pricedProductId
        )
      ).blockingGet()

      pricedProductId =
        expensesRegisterDatabase.productDao.insert("Tosta Mista Pâo Caseiro", "Restau", 3.0)
      expensesRegisterDatabase.placeDao.insert(
        PlacePricedProductCrossRef(
          placeName,
          pricedProductId
        )
      ).blockingGet()
    }
  }
}
