package neuro.expenses.register.database

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.data.ExpensesRegisterDatabase
import neuro.expenses.register.data.model.RoomCategory
import neuro.expenses.register.data.model.place.LatLng
import neuro.expenses.register.data.model.place.PlacePlaceProductCrossRef
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
      val bitoque = "bitoque"
      val vizinha = "vizinha"

      placeDao.insert(
        RoomPlace(
          bitoque.replaceFirstChar { it.uppercase() },
          LatLng(37.091495, -8.2475677)
        )
      ).blockingGet()
      placeDao.insert(
        RoomPlace(
          vizinha.replaceFirstChar { it.uppercase() },
          LatLng(37.098297, -8.2514809)
        )
      ).blockingGet()

      var placeProductId: Long
      placeProductId =
        expensesRegisterDatabase.productDao.insert(
          "Sagres Média 0,33cl",
          "Borga",
          1.1,
          1.0,
          "https://thexicos-wp.ams3.digitaloceanspaces.com/uploads/sites/5/2022/07/sagr.png"
        )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          vizinha,
          placeProductId
        )
      ).blockingGet()

      placeProductId =
        expensesRegisterDatabase.productDao.insert(
          "Sagres Média 0,33cl",
          "Borga",
          1.3,
          1.0,
          "https://thexicos-wp.ams3.digitaloceanspaces.com/uploads/sites/5/2022/07/sagr.png"
        )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          bitoque,
          placeProductId
        )
      ).blockingGet()

      placeProductId =
        expensesRegisterDatabase.productDao.insert(
          "Super Bock Média 0,33cl",
          "Borga",
          1.3,
          1.0,
          "https://media.recheio.pt/catalogo/media/catalog/product/cache/1/image/900x900/9df78eab33525d08d6e5fb8d27136e95/6/0/60710_1.jpg"
        )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          bitoque,
          placeProductId
        )
      ).blockingGet()

      placeProductId =
        expensesRegisterDatabase.productDao.insert(
          "Mini Cristal 0,20cl",
          "Borga",
          1.1,
          1.0,
          "https://www.apolonia.com/fotos/produtos/706574_01_14.05.18_g.jpg"
        )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          bitoque,
          placeProductId
        )
      ).blockingGet()

      placeProductId =
        expensesRegisterDatabase.productDao.insert(
          "Mini Sagres 0,25cl",
          "Borga",
          1.1,
          1.0,
          "https://www.n9v.pt/media/catalog/product/9/7/97fed08c9e16c6ff96434828b726d804447674cf_sagres_mini_pp7dowcqz4ocqjmc.png?quality=80&bg-color=255,255,255&fit=bounds&height=759&width=759&canvas=759:759&format=jpeg"
        )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          bitoque,
          placeProductId
        )
      ).blockingGet()

      placeProductId = expensesRegisterDatabase.productDao.insert(
        "Chicharricos",
        "Restau",
        1.5,
        1.0,
        "https://www.reinobrilhante.pt/imagens/produtos/PastedGraphic_6.png"
      )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          bitoque,
          placeProductId
        )
      ).blockingGet()

      placeProductId = expensesRegisterDatabase.productDao.insert(
        "Twix 50g",
        "Restau",
        1.5,
        1.0,
        "https://www.spar.pt/images/thumbs/0000488_choc-twix-single-50gr_550.jpeg"
      )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          bitoque,
          placeProductId
        )
      ).blockingGet()

      placeProductId =
        expensesRegisterDatabase.productDao.insert(
          "Tosta Mista Pâo Caseiro",
          "Restau",
          3.0,
          1.0,
          "https://www.iguaria.com/wp-content/uploads/2016/03/Iguaria_Tosta-de-Bacon-Queijo-Fiambre.jpg"
        )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          bitoque,
          placeProductId
        )
      ).blockingGet()
    }
  }
}
