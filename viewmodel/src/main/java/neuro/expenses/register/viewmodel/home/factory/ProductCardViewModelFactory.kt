package neuro.expenses.register.viewmodel.home.factory

import androidx.compose.runtime.State
import neuro.expenses.register.viewmodel.home.ProductCardViewModel
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import java.util.*

interface ProductCardViewModelFactory {
  fun create(
    productCardModel: ProductCardModel,
    calendar: State<Calendar>
  ): ProductCardViewModel
}