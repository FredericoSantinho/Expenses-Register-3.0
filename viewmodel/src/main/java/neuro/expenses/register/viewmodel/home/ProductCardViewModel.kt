package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import io.reactivex.rxjava3.disposables.CompositeDisposable
import neuro.expenses.register.common.schedulers.SchedulerProvider
import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.usecase.register.RegisterExpenseUseCase
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import java.util.*

class ProductCardViewModel(
  productCardModel: ProductCardModel,
  val calendar: State<Calendar>,
  private val registerExpenseUseCase: RegisterExpenseUseCase,
  private val schedulerProvider: SchedulerProvider,
  private val disposable: CompositeDisposable = CompositeDisposable()
) {
  val description = mutableStateOf(productCardModel.description)
  val category = mutableStateOf(productCardModel.category)
  val place = mutableStateOf(productCardModel.place)
  val price = mutableStateOf(productCardModel.price)
  val amount = mutableStateOf(productCardModel.amount)
  val iconUrl = mutableStateOf(productCardModel.iconUrl)

  fun onCardClick() {
    disposable.add(
      registerExpenseUseCase.registerExpense(
        ExpenseDto(
          description.value,
          category.value,
          place.value,
          price.value.substring(0, price.value.length - 2).toDouble(),
          amount.value,
          calendar.value
      )
    ).subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui()).subscribe {
    })
  }

  fun clear() {
    disposable.clear()
  }
}