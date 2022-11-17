package neuro.expenses.register.entity.controller.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.PlaceProduct
import neuro.expenses.register.entity.controller.category.GetCategory
import neuro.expenses.register.entity.controller.place.GetPlace

class GetOrCreatePlaceProductImpl(
  private val getPlaceProduct: GetPlaceProduct,
  private val getOrCreateProduct: GetOrCreateProduct,
  private val savePlaceProduct: SavePlaceProduct,
  private val generatePlaceProductId: GeneratePlaceProductId,
  private val getCategory: GetCategory,
  private val getPlace: GetPlace
) : GetOrCreatePlaceProduct {

  override fun getOrCreatePlaceProduct(
    description: String,
    category: String,
    price: Double,
    variableAmount: Boolean,
    place: String
  ): Single<PlaceProduct> {
    return getPlace.getPlace(place.lowercase()).toSingle().flatMap { loadedPlace ->
      getCategory.getCategory(category.lowercase()).toSingle().flatMap { loadedCategory ->
        getPlaceProduct.getPlaceProduct(
          description.lowercase(),
          loadedCategory.name,
          price,
          loadedPlace
        ).switchIfEmpty(
          getOrCreateProduct.getOrCreateProduct(description, variableAmount).flatMap { product ->
            generatePlaceProductId.newId().flatMap { placeProductId ->
              val placeProduct = PlaceProduct(
                placeProductId,
                product,
                loadedCategory,
                price,
                loadedPlace.id
              )
              savePlaceProduct.savePlaceProduct(placeProduct).andThen(Single.just(placeProduct))
            }
          }
        )
      }
    }
  }
}