package neuro.expenses.register.viewmodel.manual.register

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.viewmodel.bill.IBillCardViewModel
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiEvent.UiEvent
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiState.UiState
import java.util.*

interface IManualRegisterViewModel {
  val billCardViewModel: IBillCardViewModel
  val description: MutableState<String>
  val category: MutableState<String>
  val place: MutableState<String>
  val price: MutableState<String>
  val amount: MutableState<String>
  val total: MutableState<String>
  val calendar: MutableState<Calendar>
  val categoriesNames: Observable<List<String>>

  val uiState: State<UiState>
  val uiEvent: State<UiEvent?>

  fun onComposition()
  fun onNearestPlaceButton()
  fun onRegisterButton()
  fun onPriceChange()
  fun onAmountChange()
  fun onDescriptionChange()
  fun onCategoryChange()
  fun onPlaceChange()
  fun eventConsumed()
}
