package neuro.expenses.register.ui.home.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.exchangebot.common.schedulers.SchedulerProvider
import com.google.android.gms.maps.model.LatLng
import neuro.expenses.register.common.viewmodel.BaseViewModel
import neuro.expenses.register.common.viewmodel.asState
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCase
import neuro.expenses.register.domain.usecase.location.GetCurrentLocationUseCase
import neuro.expenses.register.domain.usecase.place.GetNearestPlacesUseCase
import neuro.expenses.register.ui.common.bill.BillViewModel
import neuro.expenses.register.ui.common.bill.FeedLastBillViewModel
import neuro.expenses.register.ui.common.mapper.LatLngMapper
import neuro.expenses.register.ui.home.factory.ProductCardViewModelFactory
import neuro.expenses.register.ui.home.mapper.ProductCardModelMapper

class HomeViewModel(
  private val getNearestPlacesUseCase: GetNearestPlacesUseCase,
  private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
  private val getCalendarUseCase: GetCalendarUseCase,
  private val feedLastBillViewModel: FeedLastBillViewModel,
  private val productCardViewModelFactory: ProductCardViewModelFactory,
  private val latLngMapper: LatLngMapper,
  private val productCardModelMapper: ProductCardModelMapper,
  val billViewModel: BillViewModel,
  schedulerProvider: SchedulerProvider,
  private val nearestPlacesLimit: Int = 5,
  private val zoom: Float = 19.0f
) : BaseViewModel(schedulerProvider) {
  private val places = mutableStateOf(emptyList<PlaceDto>())
  val placesNames = mutableStateOf(emptyList<String>())
  private var selectedPlaceIndex = 0

  val calendar = mutableStateOf(getCalendarUseCase.getCalendar())
  val productsListViewModel: ProductsListViewModel = newProductsListViewModel()

  private val _uiState = mutableStateOf<UiState>(UiState.Loading)
  val uiState = _uiState.asState()

  init {
    getCurrentLocationUseCase.getCurrentLocation()
      .flatMap { getNearestPlacesUseCase.getNearestPlaces(it, nearestPlacesLimit) }
      .baseSubscribe { nearestPlaces ->
        places.value = nearestPlaces
        placesNames.value = nearestPlaces.map { placeDto -> placeDto.name }
        productsListViewModel.setProducts(nearestPlaces.get(selectedPlaceIndex))
        val latLng = latLngMapper.map(nearestPlaces.get(selectedPlaceIndex).latLngDto)
        _uiState.value = UiState.Ready(latLng, zoom)
      }
    disposable.add(feedLastBillViewModel.subscribe())
  }

  fun onSelectedPlace(index: Int) {
    selectedPlaceIndex = index
    val placeDto = places.value.get(index)
    val latLng = latLngMapper.map(placeDto.latLngDto)
    _uiState.value = UiState.Ready(latLng, zoom)
    productsListViewModel.setProducts(placeDto)
  }

  override fun onCleared() {
    super.onCleared()
    productsListViewModel.clear()
  }

  private fun newProductsListViewModel() =
    ProductsListViewModel(
      productCardViewModelFactory,
      productCardModelMapper,
      calendar
    )
}

sealed class UiState() {
  object Loading : UiState()
  class Ready(val latLng: LatLng, val zoom: Float) : UiState()
}
