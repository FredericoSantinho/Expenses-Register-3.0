package neuro.expenses.register.domain.usecase.populate

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.*
import neuro.expenses.register.domain.usecase.category.SaveCategoryUseCase
import neuro.expenses.register.domain.usecase.place.SavePlaceUseCase
import neuro.expenses.register.domain.usecase.product.GetOrCreatePlaceProductUseCase

class PrePopulateImpl(
  private val saveCategoryUseCase: SaveCategoryUseCase,
  private val getOrCreatePlaceProductUseCase: GetOrCreatePlaceProductUseCase,
  private val savePlaceUseCase: SavePlaceUseCase
) : PrePopulate {
  override fun prePopulate(): Completable {
    return Completable.defer {
      val cafe = CategoryDto(1L, "Café")
      val borga = CategoryDto(2L, "Borga")
      val restau = CategoryDto(3L, "Restau")

      saveCategoryUseCase.saveCategories(listOf(cafe, borga, restau)).andThen(Completable.defer {
        val sagresMedia = PlaceProductDto(
          1L,
          ProductDto(
            1L,
            "Sagres Média 0,33cl",
            false,
            "https://thexicos-wp.ams3.digitaloceanspaces.com/uploads/sites/5/2022/07/sagr.png"
          ),
          borga,
          1.1,
        )
        getOrCreatePlaceProductUseCase.getOrCreatePlaceProduct(sagresMedia).flatMapCompletable {
          val vizinha =
            PlaceDto(1L, "Vizinha", listOf(sagresMedia), LatLngDto(37.098297, -8.2514809))
          savePlaceUseCase.savePlace(vizinha)
        }.andThen(Completable.defer {
          val products = buildBitoqueProducts(borga, restau, sagresMedia)
          val bitoque = PlaceDto(2L, "Bitoque", products, LatLngDto(37.091495, -8.2475677))
          Observable.fromIterable(products).filter { it != sagresMedia }
            .flatMapSingle { getOrCreatePlaceProductUseCase.getOrCreatePlaceProduct(it) }
            .flatMapCompletable { savePlaceUseCase.savePlace(bitoque) }
        }).andThen(Completable.defer {
          val caneca50 = PlaceProductDto(
            8L,
            ProductDto(
              8L, "Caneca 50cl", false, "https://www1.tescoma.com/images/zbozi/hires/309024.jpg?1"
            ),
            borga,
            2.5,
          )
          val longoBar =
            PlaceDto(3L, "Longo Bar", listOf(caneca50), LatLngDto(37.0975346, -8.2283147))
          getOrCreatePlaceProductUseCase.getOrCreatePlaceProduct(caneca50)
            .flatMapCompletable { savePlaceUseCase.savePlace(longoBar) }
        })
      })
    }

  }

  private fun buildBitoqueProducts(
    borga: CategoryDto, restau: CategoryDto, sagresMedia: PlaceProductDto
  ): List<PlaceProductDto> {
    val superBockMedia = PlaceProductDto(
      2L,
      ProductDto(
        2L,
        "Super Bock Média 0,33cl",
        false,
        "https://media.recheio.pt/catalogo/media/catalog/product/cache/1/image/900x900/9df78eab33525d08d6e5fb8d27136e95/6/0/60710_1.jpg"
      ),
      borga,
      1.3,
    )
    val miniCristal = PlaceProductDto(
      3L,
      ProductDto(
        3L,
        "Mini Cristal 0,20cl",
        false,
        "https://www.apolonia.com/fotos/produtos/706574_01_14.05.18_g.jpg"
      ),
      borga,
      1.1,
    )
    val miniSagres = PlaceProductDto(
      4L,
      ProductDto(
        4L,
        "Mini Sagres 0,25cl",
        false,
        "https://www.n9v.pt/media/catalog/product/9/7/97fed08c9e16c6ff96434828b726d804447674cf_sagres_mini_pp7dowcqz4ocqjmc.png?quality=80&bg-color=255,255,255&fit=bounds&height=759&width=759&canvas=759:759&format=jpeg"
      ),
      borga,
      1.1,
    )
    val chicharricos = PlaceProductDto(
      5L,
      ProductDto(
        5L,
        "Chicharricos",
        false,
        "https://www.reinobrilhante.pt/imagens/produtos/PastedGraphic_6.png"
      ),
      restau,
      1.5,
    )
    val twix50 = PlaceProductDto(
      6L,
      ProductDto(
        6L,
        "Twix 50g",
        false,
        "https://www.spar.pt/images/thumbs/0000488_choc-twix-single-50gr_550.jpeg"
      ),
      restau,
      1.5,
    )
    val tostaMistaCaseira = PlaceProductDto(
      7L,
      ProductDto(
        7L,
        "Tosta Mista Pâo Caseiro",
        false,
        "https://www.iguaria.com/wp-content/uploads/2016/03/Iguaria_Tosta-de-Bacon-Queijo-Fiambre.jpg"
      ),
      restau,
      3.0,
    )
    return listOf(
      sagresMedia, superBockMedia, miniCristal, miniSagres, chicharricos, twix50, tostaMistaCaseira
    )
  }
}