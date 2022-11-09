package neuro.expenses.register.ui.home.factory

import androidx.compose.runtime.State
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.ui.home.viewmodel.ProductCardViewModel
import java.util.*

interface ProductCardViewModelFactory {
  fun create(
    productDto: ProductDto,
    place: String,
    calendar: State<Calendar>
  ): ProductCardViewModel
}