package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import neuro.expenses.register.viewmodel.bill.BillViewModel
import java.util.*

interface IHomeViewModel : OnProductCardClick {
  val billViewModel: BillViewModel
  val placesNames: MutableState<List<String>>
  val calendar: MutableState<Calendar>
  val productsListViewModel: ProductsListViewModel
  val uiState: State<UiState>

  fun onSelectedPlace(index: Int)
  fun newProductsListViewModel(): ProductsListViewModel
}