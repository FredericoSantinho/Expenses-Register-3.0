package neuro.expenses.register.viewmodel.home

import neuro.expenses.register.viewmodel.home.model.ProductCardModel

interface OnProductCardClick {
  fun onProductCardClick(productCardModel: ProductCardModel)
  fun onProductCardLongClick(productCardModel: ProductCardModel)
}