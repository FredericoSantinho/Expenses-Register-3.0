package neuro.expenses.register.viewmodel.home

import neuro.expenses.register.viewmodel.home.model.PlaceProductCardModel

interface OnProductCardClick {
  fun onProductCardClick(placeProductCardModel: PlaceProductCardModel)
  fun onProductCardLongClick(placeProductCardModel: PlaceProductCardModel)
}