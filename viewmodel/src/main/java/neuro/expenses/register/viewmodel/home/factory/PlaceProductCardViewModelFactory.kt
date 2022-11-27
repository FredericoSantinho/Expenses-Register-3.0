package neuro.expenses.register.viewmodel.home.factory

import neuro.expenses.register.viewmodel.home.PlaceProductCardViewModel
import neuro.expenses.register.viewmodel.home.model.PlaceProductCardModel

interface PlaceProductCardViewModelFactory {
  fun create(
    placeProductCardModel: PlaceProductCardModel
  ): PlaceProductCardViewModel
}