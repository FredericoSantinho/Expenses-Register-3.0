package neuro.expenses.register.entity.controller.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.Product
import neuro.expenses.register.entity.controller.category.GetCategory
import neuro.expenses.register.entity.controller.place.GetPlace

class GetOrCreateProductImpl(
  private val getProduct: GetProduct,
  private val saveProduct: SaveProduct,
  private val generateProductId: GenerateProductId,
  private val getCategory: GetCategory,
  private val getPlace: GetPlace
) : GetOrCreateProduct {

  override fun getOrCreateProduct(
    description: String,
    category: String,
    price: Double,
    variableAmount: Boolean,
    place: String
  ): Single<Product> {
    return getProduct.getProduct(
      description.lowercase(),
      category.lowercase(),
      price
    ).switchIfEmpty(generateProductId.newId().flatMap { productId ->
      getCategory.getCategory(category.lowercase()).toSingle().flatMap { category ->
        getPlace.getPlace(place.lowercase()).toSingle().flatMap { place ->
          val product = Product(
            productId,
            description,
            category,
            price,
            place.id,
            variableAmount
          )
          saveProduct.saveProduct(product).andThen(Single.just(product))
        }
      }
    }
    )
  }
}