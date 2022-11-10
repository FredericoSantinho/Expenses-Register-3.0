package neuro.expenses.register.ui.home.factory

import androidx.compose.runtime.State
import com.exchangebot.common.schedulers.SchedulerProvider
import neuro.expenses.register.domain.usecase.register.RegisterExpenseUseCase
import neuro.expenses.register.ui.home.model.ProductCardModel
import neuro.expenses.register.ui.home.viewmodel.ProductCardViewModel
import java.util.*

class ProductCardViewModelFactoryImpl(
  private val registerExpenseUseCase: RegisterExpenseUseCase,
  private val schedulerProvider: SchedulerProvider
) : ProductCardViewModelFactory {
  override fun create(
    productCardModel: ProductCardModel,
    calendar: State<Calendar>
  ): ProductCardViewModel {
    return ProductCardViewModel(
      productCardModel,
      calendar,
      registerExpenseUseCase,
      schedulerProvider
    )
  }
}