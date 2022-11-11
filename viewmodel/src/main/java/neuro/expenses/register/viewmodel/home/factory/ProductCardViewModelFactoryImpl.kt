package neuro.expenses.register.viewmodel.home.factory

import androidx.compose.runtime.State
import neuro.expenses.register.viewmodel.home.IHomeViewModel
import neuro.expenses.register.viewmodel.home.ProductCardViewModel
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import java.util.*

class ProductCardViewModelFactoryImpl(private val homeViewModel: IHomeViewModel) :
  ProductCardViewModelFactory {
  override fun create(
    productCardModel: ProductCardModel,
    calendar: State<Calendar>
  ): ProductCardViewModel {
    return ProductCardViewModel(
      homeViewModel,
      productCardModel,
      calendar
    )
  }
}