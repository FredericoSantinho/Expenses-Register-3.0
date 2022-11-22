package neuro.expenses.register.entity.controller.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.PlaceProduct
import neuro.expenses.register.entity.controller.category.GetCategory

class GetOrCreatePlaceProductImpl(
  private val getPlaceProduct: GetPlaceProduct,
  private val getOrCreateProduct: GetOrCreateProduct,
  private val savePlaceProduct: SavePlaceProduct,
  private val generatePlaceProductId: GeneratePlaceProductId,
  private val getCategory: GetCategory
) : GetOrCreatePlaceProduct {

  override fun getOrCreatePlaceProduct(
    description: String, category: String, price: Double, variableAmount: Boolean
  ): Single<PlaceProduct> {
    return getCategory.getCategory(category.lowercase()).toSingle().flatMap { loadedCategory ->
      getPlaceProduct.getPlaceProduct(
        description.lowercase(), loadedCategory.name, price
      ).switchIfEmpty(getOrCreateProduct.getOrCreateProduct(description, variableAmount)
        .flatMap { product ->
          generatePlaceProductId.newId().flatMap { placeProductId ->
            val placeProduct = PlaceProduct(placeProductId, product, loadedCategory, price)
            savePlaceProduct.savePlaceProduct(placeProduct).andThen(Single.just(placeProduct))
          }
        })
    }
  }
}