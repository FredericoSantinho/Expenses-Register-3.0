package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCase
import neuro.expenses.register.domain.usecase.location.GetCurrentLocationUseCase
import neuro.expenses.register.domain.usecase.place.GetNearestPlacesUseCase
import neuro.expenses.register.domain.usecase.register.RegisterExpenseUseCase
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.bill.FeedLastBillViewModel
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.asState
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.home.factory.ProductCardViewModelFactoryImpl
import neuro.expenses.register.viewmodel.home.mapper.LatLngModelMapper
import neuro.expenses.register.viewmodel.home.mapper.ProductCardModelMapper
import neuro.expenses.register.viewmodel.home.model.LatLngModel
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import java.util.*

class HomeViewModel(
  private val getNearestPlacesUseCase: GetNearestPlacesUseCase,
  private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
  private val getCalendarUseCase: GetCalendarUseCase,
  private val registerExpenseUseCase: RegisterExpenseUseCase,
  private val feedLastBillViewModel: FeedLastBillViewModel,
  private val latLngModelMapper: LatLngModelMapper,
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
        val latLng = latLngModelMapper.map(nearestPlaces.get(selectedPlaceIndex).latLngDto)
        _uiState.value = UiState.Ready(latLng, zoom)
      }
    disposable.add(feedLastBillViewModel.subscribe())
  }

  fun onSelectedPlace(index: Int) {
    selectedPlaceIndex = index
    val placeDto = places.value.get(index)
    val latLng = latLngModelMapper.map(placeDto.latLngDto)
    _uiState.value = UiState.Ready(latLng, zoom)
    productsListViewModel.setProducts(placeDto)
  }

  private fun newProductsListViewModel() =
    ProductsListViewModel(
      ProductCardViewModelFactoryImpl(this),
      productCardModelMapper,
      calendar
    )

  fun onProductCardClick(productCardModel: ProductCardModel, calendar: Calendar) {
    registerExpenseUseCase.registerExpense(
      productCardModelMapper.map(productCardModel, calendar)
    ).baseSubscribe {
    }
  }
}

sealed class UiState() {
  object Loading : UiState()
  class Ready(val latLngModel: LatLngModel, val zoom: Float) : UiState()
}
