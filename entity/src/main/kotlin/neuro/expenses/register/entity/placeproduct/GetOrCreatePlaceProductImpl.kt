package neuro.expenses.register.entity.placeproduct

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.category.GetCategory
import neuro.expenses.register.entity.model.PlaceProduct
import neuro.expenses.register.entity.product.GetOrCreateProduct

class GetOrCreatePlaceProductImpl(
  private val getPlaceProduct: GetPlaceProduct,
  private val getOrCreateProduct: GetOrCreateProduct,
  private val savePlaceProduct: SavePlaceProduct,
  private val generatePlaceProductId: GeneratePlaceProductId,
  private val getCategory: GetCategory
) : GetOrCreatePlaceProduct {

  override fun getOrCreatePlaceProduct(
    description: String, category: String, price: Double, variableAmount: Boolean, iconUrl: String
  ): Single<PlaceProduct> {
    return getCategory.getCategory(category.lowercase()).toSingle().flatMap { loadedCategory ->
      getPlaceProduct.getPlaceProduct(
        description.lowercase(), loadedCategory.name, price
      ).switchIfEmpty(getOrCreateProduct.getOrCreateProduct(description, variableAmount, iconUrl)
        .flatMap { product ->
          generatePlaceProductId.newId().flatMap { placeProductId ->
            val placeProduct = PlaceProduct(placeProductId, product, loadedCategory, price)
            savePlaceProduct.savePlaceProduct(placeProduct).andThen(Single.just(placeProduct))
          }
        })
    }
  }
}