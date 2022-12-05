package neuro.expenses.register.presentation.ui.manual.register.dummy

import androidx.compose.runtime.mutableStateOf
import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.viewmodel.bill.BillCardViewModel
import neuro.expenses.register.viewmodel.manual.register.IManualRegisterViewModel
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiState
import java.util.*

class DummyManualRegisterViewModel() : IManualRegisterViewModel {
  override val billCardViewModel = BillCardViewModel()
  override val description = mutableStateOf("")
  override val category = mutableStateOf("")
  override val place = mutableStateOf("")
  override val price = mutableStateOf("")
  override val amount = mutableStateOf("")
  override val total = mutableStateOf("30000000.00 €")
  override val calendar = mutableStateOf(Calendar.getInstance())
  override val categoriesNames = Observable.just(emptyList<String>())

  override val uiState = mutableStateOf(ManualRegisterUiState.UiState.Ready)
  override val uiEvent = mutableStateOf(null)

  override fun onNearestPlaceButton() {
  }

  override fun onRegisterButton() {
  }

  override fun onPriceChange() {
  }

  override fun onAmountChange() {
  }

  override fun onDescriptionChange() {
  }

  override fun onCategoryChange() {
  }

  override fun onPlaceChange() {
  }

  override fun eventConsumed() {
  }

  override fun onComposition() {
  }
}