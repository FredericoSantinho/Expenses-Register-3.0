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

      val cafeId = 1L
      val borgaId = 2L
      val restauId = 3L
      categoryDao.insert(
        listOf(
          RoomCategory(cafeId, "Café"),
          RoomCategory(borgaId, "Borga"),
          RoomCategory(restauId, "Restau")
        )
      ).blockingGet()

      val placeDao = expensesRegisterDatabase.placeDao
      val bitoque = "Bitoque"
      val vizinha = "Vizinha"
      val longo = "Longo Bar"
      val bitoqueId = 1L
      val vizinhaId = 2L
      val longoId = 3L

      placeDao.insert(
        RoomPlace(
          bitoqueId,
          bitoque,
          LatLng(37.091495, -8.2475677)
        )
      ).blockingGet()
      placeDao.insert(
        RoomPlace(
          vizinhaId,
          vizinha,
          LatLng(37.098297, -8.2514809)
        )
      ).blockingGet()

      var placeProductId: Long
      placeProductId =
        expensesRegisterDatabase.productDao.insert(
          1,
          "Sagres Média 0,33cl",
          borgaId,
          1.1,
          "https://thexicos-wp.ams3.digitaloceanspaces.com/uploads/sites/5/2022/07/sagr.png",
          false,
          vizinhaId
        )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          vizinhaId,
          placeProductId
        )
      ).blockingGet()

      placeProductId =
        expensesRegisterDatabase.productDao.insert(
          2,
          "Sagres Média 0,33cl",
          borgaId,
          1.3,
          "https://thexicos-wp.ams3.digitaloceanspaces.com/uploads/sites/5/2022/07/sagr.png",
          false,
          bitoqueId
        )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          bitoqueId,
          placeProductId
        )
      ).blockingGet()

      placeProductId =
        expensesRegisterDatabase.productDao.insert(
          3,
          "Super Bock Média 0,33cl",
          borgaId,
          1.3,
          "https://media.recheio.pt/catalogo/media/catalog/product/cache/1/image/900x900/9df78eab33525d08d6e5fb8d27136e95/6/0/60710_1.jpg",
          false,
          bitoqueId
        )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          bitoqueId,
          placeProductId
        )
      ).blockingGet()

      placeProductId =
        expensesRegisterDatabase.productDao.insert(
          4,
          "Mini Cristal 0,20cl",
          borgaId,
          1.1,
          "https://www.apolonia.com/fotos/produtos/706574_01_14.05.18_g.jpg",
          false,
          bitoqueId
        )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          bitoqueId,
          placeProductId
        )
      ).blockingGet()

      placeProductId =
        expensesRegisterDatabase.productDao.insert(
          5,
          "Mini Sagres 0,25cl",
          borgaId,
          1.1,
          "https://www.n9v.pt/media/catalog/product/9/7/97fed08c9e16c6ff96434828b726d804447674cf_sagres_mini_pp7dowcqz4ocqjmc.png?quality=80&bg-color=255,255,255&fit=bounds&height=759&width=759&canvas=759:759&format=jpeg",
          false,
          bitoqueId
        )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          bitoqueId,
          placeProductId
        )
      ).blockingGet()

      placeProductId = expensesRegisterDatabase.productDao.insert(
        6,
        "Chicharricos",
        restauId,
        1.5,
        "https://www.reinobrilhante.pt/imagens/produtos/PastedGraphic_6.png",
        false,
        bitoqueId
      )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          bitoqueId,
          placeProductId
        )
      ).blockingGet()

      placeProductId = expensesRegisterDatabase.productDao.insert(
        7,
        "Twix 50g",
        restauId,
        1.5,
        "https://www.spar.pt/images/thumbs/0000488_choc-twix-single-50gr_550.jpeg",
        false,
        bitoqueId
      )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          bitoqueId,
          placeProductId
        )
      ).blockingGet()

      placeProductId =
        expensesRegisterDatabase.productDao.insert(
          8,
          "Tosta Mista Pâo Caseiro",
          restauId,
          3.0,
          "https://www.iguaria.com/wp-content/uploads/2016/03/Iguaria_Tosta-de-Bacon-Queijo-Fiambre.jpg",
          false,
          bitoqueId
        )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          bitoqueId,
          placeProductId
        )
      ).blockingGet()

      placeDao.insert(
        RoomPlace(
          longoId,
          longo,
          LatLng(37.0975346, -8.2283147)
        )
      ).blockingGet()
      placeProductId =
        expensesRegisterDatabase.productDao.insert(
          9,
          "Caneca 50cl",
          borgaId,
          2.5,
          "https://www1.tescoma.com/images/zbozi/hires/309024.jpg?1",
          false,
          longoId
        )
      expensesRegisterDatabase.placeDao.insert(
        PlacePlaceProductCrossRef(
          longoId,
          placeProductId
        )
      ).blockingGet()
    }
  }
}
