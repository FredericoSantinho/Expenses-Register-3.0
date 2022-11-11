package neuro.expenses.register.viewmodel.home

import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import java.util.*

interface OnProductCardClick {
  fun onProductCardClick(productCardModel: ProductCardModel, calendar: Calendar)
  fun onProductCardLongClick(productCardModel: ProductCardModel, calendar: Calendar)
}