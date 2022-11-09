package neuro.expenses.register.ui.home.view.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.exchangebot.common.schedulers.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.usecase.register.RegisterExpenseUseCase
import neuro.expenses.register.ui.common.formatter.DoubleFormatter
import java.util.*

class ProductCardViewModel(
  productDto: ProductDto,
  place: String,
  val calendar: State<Calendar>,
  private val registerExpenseUseCase: RegisterExpenseUseCase,
  private val doubleFormatter: DoubleFormatter,
  private val schedulerProvider: SchedulerProvider,
  currency: String = "€",
  private val disposable: CompositeDisposable = CompositeDisposable()
) {
  val description = mutableStateOf(productDto.description)
  val category = mutableStateOf(productDto.category)
  val place = mutableStateOf(place)
  val price = mutableStateOf(doubleFormatter.format(productDto.price) + ' ' + currency)
  val amount = mutableStateOf(productDto.defaultAmount)
  val iconUrl = mutableStateOf(productDto.iconUrl)

  fun onCardClick() {
    disposable.add(registerExpenseUseCase.registerExpense(
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