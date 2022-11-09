package neuro.expenses.register.ui.home.factory

import androidx.compose.runtime.State
import com.exchangebot.common.schedulers.SchedulerProvider
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.usecase.register.RegisterExpenseUseCase
import neuro.expenses.register.ui.common.formatter.DoubleFormatter
import neuro.expenses.register.ui.home.viewmodel.ProductCardViewModel
import java.util.*

class ProductCardViewModelFactoryImpl(
  private val registerExpenseUseCase: RegisterExpenseUseCase,
  private val doubleFormatter: DoubleFormatter,
  private val schedulerProvider: SchedulerProvider,
  private val currency: String = "€"
) : ProductCardViewModelFactory {
  override fun create(
    productDto: ProductDto,
    place: String,
    calendar: State<Calendar>
  ): ProductCardViewModel {
    return ProductCardViewModel(
      productDto,
      place,
      calendar,
      registerExpenseUseCase,
      doubleFormatter,
      schedulerProvider,
      currency
    )
  }
}