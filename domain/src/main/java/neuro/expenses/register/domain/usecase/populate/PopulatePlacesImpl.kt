package neuro.expenses.register.domain.usecase.populate

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.*
import neuro.expenses.register.domain.usecase.category.SaveCategoryUseCase
import neuro.expenses.register.domain.usecase.place.SavePlaceUseCase
import neuro.expenses.register.domain.usecase.product.GetOrCreatePlaceProductUseCase

class PopulatePlacesImpl(
  private val saveCategoryUseCase: SaveCategoryUseCase,
  private val getOrCreatePlaceProductUseCase: GetOrCreatePlaceProductUseCase,
  private val savePlaceUseCase: SavePlaceUseCase,
) : PopulatePlaces {
  override fun populatePlaces(): Completable {
    return Completable.defer {
      val cafe = CategoryDto(1L, "Café")
      val borga = CategoryDto(2L, borgaName)
      val restau = CategoryDto(3L, restauName)

      saveCategoryUseCase.saveCategories(listOf(cafe, borga, restau)).andThen(Completable.defer {
        val sagresMedia = PlaceProductDto(
          1L,
          ProductDto(
            1L,
            sagresMediaDescription,
            false,
            "https://thexicos-wp.ams3.digitaloceanspaces.com/uploads/sites/5/2022/07/sagr.png"
          ),
          borga,
          price11,
        )
        getOrCreatePlaceProductUseCase.getOrCreatePlaceProduct(sagresMedia).flatMapCompletable {
          val vizinha =
            PlaceDto(1L, vizinhaName, listOf(sagresMedia), LatLngDto(37.098297, -8.2514809))
          savePlaceUseCase.savePlace(vizinha)
        }.andThen(Completable.defer {
          val products = buildBitoqueProducts(borga, restau)
          val bitoque = PlaceDto(2L, bitoqueName, products, LatLngDto(37.091495, -8.2475677))
          Observable.fromIterable(products)
            .flatMapSingle { getOrCreatePlaceProductUseCase.getOrCreatePlaceProduct(it) }.toList()
            .flatMapCompletable { savePlaceUseCase.savePlace(bitoque) }
        }).andThen(Completable.defer {
          val caneca50 = PlaceProductDto(
            8L,
            ProductDto(
              8L,
              caneca50Description,
              false,
              "https://www1.tescoma.com/images/zbozi/hires/309024.jpg?1"
            ),
            borga,
            price25,
          )
          val longoBar =
            PlaceDto(3L, longoBarName, listOf(caneca50), LatLngDto(37.0975346, -8.2283147))
          getOrCreatePlaceProductUseCase.getOrCreatePlaceProduct(caneca50)
            .flatMapCompletable { savePlaceUseCase.savePlace(longoBar) }
        })
      })
    }
  }
}

private fun buildBitoqueProducts(
  borga: CategoryDto, restau: CategoryDto
): List<PlaceProductDto> {
  val sagresMedia = PlaceProductDto(
    1L,
    ProductDto(
      1L,
      sagresMediaDescription,
      false,
      "https://thexicos-wp.ams3.digitaloceanspaces.com/uploads/sites/5/2022/07/sagr.png"
    ),
    borga,
    price13,
  )
  val superBockMedia = PlaceProductDto(
    2L,
    ProductDto(
      2L,
      superBockMediaDescription,
      false,
      "https://media.recheio.pt/catalogo/media/catalog/product/cache/1/image/900x900/9df78eab33525d08d6e5fb8d27136e95/6/0/60710_1.jpg"
    ),
    borga,
    price13,
  )
  val miniCristal = PlaceProductDto(
    3L,
    ProductDto(
      3L,
      miniCristalDescription,
      false,
      "https://www.apolonia.com/fotos/produtos/706574_01_14.05.18_g.jpg"
    ),
    borga,
    price11,
  )
  val miniSagres = PlaceProductDto(
    4L,
    ProductDto(
      4L,
      miniSagresDescription,
      false,
      "https://www.n9v.pt/media/catalog/product/9/7/97fed08c9e16c6ff96434828b726d804447674cf_sagres_mini_pp7dowcqz4ocqjmc.png?quality=80&bg-color=255,255,255&fit=bounds&height=759&width=759&canvas=759:759&format=jpeg"
    ),
    borga,
    price11,
  )
  val chicharricos = PlaceProductDto(
    5L,
    ProductDto(
      5L,
      chicharricosDescription,
      false,
      "https://www.reinobrilhante.pt/imagens/produtos/PastedGraphic_6.png"
    ),
    restau,
    price15,
  )
  val twix50 = PlaceProductDto(
    6L,
    ProductDto(
      6L,
      twixDescription,
      false,
      "https://www.spar.pt/images/thumbs/0000488_choc-twix-single-50gr_550.jpeg"
    ),
    restau,
    price15,
  )
  val tostaMistaCaseira = PlaceProductDto(
    7L,
    ProductDto(
      7L,
      tostaMistaCaseiraDescription,
      false,
      "https://www.iguaria.com/wp-content/uploads/2016/03/Iguaria_Tosta-de-Bacon-Queijo-Fiambre.jpg"
    ),
    restau,
    price30,
  )
  return listOf(
    sagresMedia, superBockMedia, miniCristal, miniSagres, chicharricos, twix50, tostaMistaCaseira
  )
}

const val borgaName = "Borga"
const val restauName = "Restau"

const val vizinhaName = "Vizinha"
const val bitoqueName = "Bitoque"
const val longoBarName = "Longo Bar"

const val sagresMediaDescription = "Sagres Média 0,33cl"
const val superBockMediaDescription = "Super Bock Média 0,33cl"
const val miniCristalDescription = "Mini Cristal 0,20cl"
const val miniSagresDescription = "Mini Sagres 0,25cl"
const val chicharricosDescription = "Chicharricos"
const val twixDescription = "Twix 50g"
const val tostaMistaCaseiraDescription = "Tosta Mista Pâo Caseiro"
const val caneca50Description = "Caneca 50cl"

const val price11 = 1.1
const val price13 = 1.3
const val price15 = 1.5
const val price25 = 2.5
const val price30 = 3.0