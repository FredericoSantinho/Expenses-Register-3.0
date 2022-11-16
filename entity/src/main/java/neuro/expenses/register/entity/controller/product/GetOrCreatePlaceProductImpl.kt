package neuro.expenses.register.entity.controller.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.PlaceProduct
import neuro.expenses.register.entity.Product
import neuro.expenses.register.entity.controller.category.GetCategory
import neuro.expenses.register.entity.controller.place.GetPlace

class GetOrCreatePlaceProductImpl(
  private val getProduct: GetProduct,
  private val saveProduct: SaveProduct,
  private val generateProductId: GenerateProductId,
  private val generatePlaceProductId: GeneratePlaceProductId,
  private val getCategory: GetCategory,
  private val getPlace: GetPlace
) : GetOrCreatePlaceProduct {

  override fun getOrCreateProduct(
    description: String,
    category: String,
    price: Double,
    variableAmount: Boolean,
    place: String
  ): Single<PlaceProduct> {
    return getPlace.getPlace(place.lowercase()).toSingle().flatMap { loadedPlace ->
      getCategory.getCategory(category.lowercase()).toSingle().flatMap { loadedCategory ->
        getProduct.getProduct(
          description.lowercase(),
          loadedCategory.name,
          price,
          loadedPlace
        ).switchIfEmpty(
          generateProductId.newId().flatMap { productId ->
            generatePlaceProductId.newId().flatMap { placeProductId ->
              val placeProduct = PlaceProduct(
                placeProductId,
                Product(productId, description, variableAmount),
                loadedCategory,
                price,
                loadedPlace.id
              )
              saveProduct.saveProduct(placeProduct).andThen(Single.just(placeProduct))
            }
          }
        )
      }
    }
  }
}