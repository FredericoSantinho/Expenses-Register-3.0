package neuro.expenses.register.ui.home.factory

import androidx.compose.runtime.State
import neuro.expenses.register.ui.home.model.ProductCardModel
import neuro.expenses.register.ui.home.viewmodel.ProductCardViewModel
import java.util.*

interface ProductCardViewModelFactory {
  fun create(
    productCardModel: ProductCardModel,
    calendar: State<Calendar>
  ): ProductCardViewModel
}