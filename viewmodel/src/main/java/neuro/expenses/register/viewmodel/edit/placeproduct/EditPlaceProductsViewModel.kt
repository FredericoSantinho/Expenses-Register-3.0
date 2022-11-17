package neuro.expenses.register.viewmodel.edit.placeproduct

import androidx.compose.runtime.mutableStateOf
import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.usecase.place.GetNearestPlacesUseCase
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.asState
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.home.OnProductCardClick
import neuro.expenses.register.viewmodel.home.ProductsListViewModel
import neuro.expenses.register.viewmodel.home.factory.ProductCardViewModelFactoryImpl
import neuro.expenses.register.viewmodel.home.mapper.ProductCardModelMapper
import neuro.expenses.register.viewmodel.home.model.ProductCardModel

class EditPlaceProductsViewModel(
  private val getNearestPlacesUseCase: GetNearestPlacesUseCase,
  private val productCardModelMapper: ProductCardModelMapper,
  val editPlaceProductViewModel: EditPlaceProductViewModel,
  schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider), OnProductCardClick {
  private val _uiState = mutableStateOf<UiState>(UiState.Ready)
  val uiState = _uiState.asState()

  val place = mutableStateOf("")
  val placesNames = Observable.just(listOf("placeA", "placeB"))

  val productsListViewModel: ProductsListViewModel = newProductsListViewModel()

  private fun newProductsListViewModel() = ProductsListViewModel(
    ProductCardViewModelFactoryImpl(this), productCardModelMapper
  )

  override fun onProductCardClick(productCardModel: ProductCardModel) {

  }

  override fun onProductCardLongClick(productCardModel: ProductCardModel) {

  }

  fun onPlaceValueChange() {
    getNearestPlacesUseCase.getNearestPlaces(LatLngDto(0.0, 0.0), 1).map { it.get(0) }
      .baseSubscribe { productsListViewModel.setProducts(it) }
  }

}

sealed class UiState {
  object Ready : UiState()
  object Editing : UiState()
}
