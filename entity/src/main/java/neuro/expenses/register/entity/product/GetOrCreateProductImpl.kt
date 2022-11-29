package neuro.expenses.register.entity.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.model.Product

class GetOrCreateProductImpl(
  private val getProduct: GetProduct,
  private val generateProductId: GenerateProductId,
  private val saveProduct: SaveProduct
) : GetOrCreateProduct {
  override fun getOrCreateProduct(
    description: String, variableAmount: Boolean, iconUrl: String
  ): Single<Product> {
    return getProduct.getProduct(description)
      .switchIfEmpty(generateProductId.newId().flatMap { productId ->
        val product = Product(productId, description, variableAmount, iconUrl)
        saveProduct.saveProduct(product).andThen(Single.just(product))
      })
  }
}