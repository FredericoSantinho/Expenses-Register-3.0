package neuro.expenses.register.viewmodel.home.factory

import androidx.compose.runtime.State
import neuro.expenses.register.viewmodel.home.OnProductCardClick
import neuro.expenses.register.viewmodel.home.ProductCardViewModel
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import java.util.*

class ProductCardViewModelFactoryImpl(private val onProductCardClick: OnProductCardClick) :
  ProductCardViewModelFactory {
  override fun create(
    productCardModel: ProductCardModel,
    calendar: State<Calendar>
  ): ProductCardViewModel {
    return ProductCardViewModel(
      onProductCardClick,
      productCardModel,
      calendar
    )
  }
}