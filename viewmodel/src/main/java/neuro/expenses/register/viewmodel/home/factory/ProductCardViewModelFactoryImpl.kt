package neuro.expenses.register.viewmodel.home.factory

import androidx.compose.runtime.State
import neuro.expenses.register.common.schedulers.SchedulerProvider
import neuro.expenses.register.domain.usecase.register.RegisterExpenseUseCase
import neuro.expenses.register.viewmodel.home.ProductCardViewModel
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
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