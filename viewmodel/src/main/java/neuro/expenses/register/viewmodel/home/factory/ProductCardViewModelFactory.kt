package neuro.expenses.register.viewmodel.home.factory

import neuro.expenses.register.viewmodel.home.ProductCardViewModel
import neuro.expenses.register.viewmodel.home.model.ProductCardModel

interface ProductCardViewModelFactory {
  fun create(
    productCardModel: ProductCardModel
  ): ProductCardViewModel
}