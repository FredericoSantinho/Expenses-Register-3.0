package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import java.util.*

interface IHomeViewModel {
  val billViewModel: BillViewModel
  val placesNames: MutableState<List<String>>
  val calendar: MutableState<Calendar>
  val productsListViewModel: ProductsListViewModel
  val uiState: State<UiState>

  fun onSelectedPlace(index: Int)
  fun newProductsListViewModel(): ProductsListViewModel
  fun onProductCardClick(productCardModel: ProductCardModel, calendar: Calendar)
}