package neuro.expenses.register.entity.controller.product

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.Product

interface GetProduct {
  fun getProduct(description: String, category: String, price: Double, place: Place): Maybe<Product>
}