package neuro.expenses.register.entity.controller.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.Expense
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
  override fun getOrCreateProduct(expense: Expense): Single<Product> {
    return getProduct.getProduct(
      expense.description.lowercase(),
      expense.category.lowercase(),
      expense.price
    ).switchIfEmpty(generateProductId.newId().flatMap { productId ->
      getCategory.getCategory(expense.category.lowercase()).toSingle().flatMap { category ->
        getPlace.getPlace(expense.place.lowercase()).toSingle().flatMap { place ->
          val product = Product(
            productId,
            expense.description,
            category,
            expense.price,
            expense.amount,
            place.id
          )
          saveProduct.saveProduct(product).andThen(Single.just(product))
        }
      }
    }
    )
  }
}