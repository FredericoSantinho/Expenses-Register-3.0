package neuro.expenses.register.viewmodel.edit.placeproduct

import androidx.compose.runtime.mutableStateOf
import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.usecase.place.GetNearestPlacesUseCase
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.home.OnProductCardClick
import neuro.expenses.register.viewmodel.home.ProductsListViewModel
import neuro.expenses.register.viewmodel.home.factory.PlaceProductCardViewModelFactoryImpl
import neuro.expenses.register.viewmodel.home.mapper.PlaceProductCardModelMapper
import neuro.expenses.register.viewmodel.home.model.PlaceProductCardModel

class EditPlaceProductsViewModel(
  private val getNearestPlacesUseCase: GetNearestPlacesUseCase,
  private val placeProductCardModelMapper: PlaceProductCardModelMapper,
  val editPlaceProductViewModel: EditPlaceProductViewModel,
  schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider), OnProductCardClick {
  private val _uiState = EditPlaceProductsUiState()
  val uiState = _uiState.uiState

  val place = mutableStateOf("")
  val placesNames = Observable.just(listOf("placeA", "placeB"))

  val productsListViewModel: ProductsListViewModel = newProductsListViewModel()

  private fun newProductsListViewModel() = ProductsListViewModel(
    PlaceProductCardViewModelFactoryImpl(this), placeProductCardModelMapper
  )

  override fun onProductCardClick(placeProductCardModel: PlaceProductCardModel) {

  }

  override fun onProductCardLongClick(placeProductCardModel: PlaceProductCardModel) {

  }

  fun onPlaceValueChange() {
    getNearestPlacesUseCase.getNearestPlaces(LatLngDto(0.0, 0.0), 1).map { it.get(0) }
      .baseSubscribe { productsListViewModel.setProducts(it) }
  }
}
