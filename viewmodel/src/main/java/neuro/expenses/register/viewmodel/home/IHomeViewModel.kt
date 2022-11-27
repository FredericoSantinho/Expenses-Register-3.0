package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.edit.placeproduct.EditPlaceProductViewModel
import java.util.*

interface IHomeViewModel {
  val billViewModel: BillViewModel
  val placesNames: MutableState<List<String>>
  val calendar: MutableState<Calendar>
  val productsListViewModel: ProductsListViewModel
  val editPlaceProductViewModel: EditPlaceProductViewModel

  val uiState: State<UiState>

  fun onComposition()
  fun onSelectedPlace(index: Int)
  fun eventConsumed()
}