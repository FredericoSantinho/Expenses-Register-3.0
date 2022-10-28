package neuro.expenses.register.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class HomeViewModel(
  val productsListViewModel: ProductsListViewModel,
  val billViewModel: BillViewModel
) : ViewModel() {
  val latLng = mutableStateOf(LatLng(37.091495, -8.2475677))
  val places = mutableStateOf(listOf("Bitoque"))
}