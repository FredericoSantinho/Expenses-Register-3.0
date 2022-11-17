package neuro.expenses.register.viewmodel.home.factory

import neuro.expenses.register.viewmodel.home.OnProductCardClick
import neuro.expenses.register.viewmodel.home.ProductCardViewModel
import neuro.expenses.register.viewmodel.home.model.ProductCardModel

class ProductCardViewModelFactoryImpl(private val onProductCardClick: OnProductCardClick) :
  ProductCardViewModelFactory {
  override fun create(productCardModel: ProductCardModel): ProductCardViewModel {
    return ProductCardViewModel(
      onProductCardClick,
      productCardModel
    )
  }
}