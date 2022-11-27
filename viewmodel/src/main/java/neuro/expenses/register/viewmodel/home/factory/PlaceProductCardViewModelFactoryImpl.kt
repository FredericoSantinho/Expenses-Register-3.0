package neuro.expenses.register.viewmodel.home.factory

import neuro.expenses.register.viewmodel.home.OnProductCardClick
import neuro.expenses.register.viewmodel.home.PlaceProductCardViewModel
import neuro.expenses.register.viewmodel.home.model.PlaceProductCardModel

class PlaceProductCardViewModelFactoryImpl(private val onProductCardClick: OnProductCardClick) :
  PlaceProductCardViewModelFactory {
  override fun create(placeProductCardModel: PlaceProductCardModel): PlaceProductCardViewModel {
    return PlaceProductCardViewModel(
      onProductCardClick,
      placeProductCardModel
    )
  }
}