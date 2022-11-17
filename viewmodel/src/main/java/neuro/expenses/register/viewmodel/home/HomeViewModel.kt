package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCase
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseUseCase
import neuro.expenses.register.domain.usecase.location.GetCurrentLocationUseCase
import neuro.expenses.register.domain.usecase.place.ObserveNearestPlacesUseCase
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.bill.FeedLastBillViewModel
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.asLiveData
import neuro.expenses.register.viewmodel.common.asState
import neuro.expenses.register.viewmodel.common.livedata.SingleLiveEvent
import neuro.expenses.register.viewmodel.common.mapper.toViewmodel
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.edit.placeproduct.EditPlaceProductViewModel
import neuro.expenses.register.viewmodel.home.factory.ProductCardViewModelFactoryImpl
import neuro.expenses.register.viewmodel.home.mapper.ProductCardModelMapper
import neuro.expenses.register.viewmodel.home.mapper.toViewModel
import neuro.expenses.register.viewmodel.home.model.CameraPositionModel
import neuro.expenses.register.viewmodel.home.model.LatLngModel
import neuro.expenses.register.viewmodel.home.model.ProductCardModel

private val lisbon = CameraPositionModel(LatLngModel(38.722252, -9.139337), 7.0f)

class HomeViewModel(
  private val observeNearestPlacesUseCase: ObserveNearestPlacesUseCase,
  private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
  private val getCalendarUseCase: GetCalendarUseCase,
  private val registerExpenseUseCase: RegisterExpenseUseCase,
  private val feedLastBillViewModel: FeedLastBillViewModel,
  private val productCardModelMapper: ProductCardModelMapper,
  override val billViewModel: BillViewModel,
  override val editPlaceProductViewModel: EditPlaceProductViewModel,
  schedulerProvider: SchedulerProvider,
  private val nearestPlacesLimit: Int = 5,
  private val zoom: Float = 19.0f,
  val initialCameraPosition: CameraPositionModel = lisbon
) : BaseViewModel(schedulerProvider), IHomeViewModel {
  private val places = mutableStateOf(emptyList<PlaceDto>())
  override val placesNames = mutableStateOf(emptyList<String>())

  override val calendar = mutableStateOf(getCalendarUseCase.getCalendar())
  override val productsListViewModel: ProductsListViewModel = newProductsListViewModel()
  private lateinit var placeDto: PlaceDto

  val selectedPlaceIndex = mutableStateOf(0)

  private val _uiState = mutableStateOf<UiState>(UiState.Loading)
  override val uiState = _uiState.asState()
  private val _uiEvent = SingleLiveEvent<UiEvent>()
  val uiEvent = _uiEvent.asLiveData()

  init {
    getCurrentLocationUseCase.getCurrentLocation().flatMapObservable {
      observeNearestPlacesUseCase.observeNearestPlaces(it, nearestPlacesLimit)
    }.baseSubscribe { nearestPlaces ->
      places.value = nearestPlaces
      placesNames.value = nearestPlaces.map { placeDto -> placeDto.name }
      placeDto = nearestPlaces.get(selectedPlaceIndex.value)
      productsListViewModel.setProducts(placeDto)
      val latLngModel = placeDto.latLngDto.toViewModel()
      _uiState.value = UiState.Ready
      _uiEvent.value = UiEvent.MoveCamera(latLngModel, zoom)
    }
    feedLastBillViewModel.observe().baseSubscribe { }
  }

  override fun onSelectedPlace(index: Int) {
    val placeDto = places.value.get(index)
    val latLngModel = placeDto.latLngDto.toViewModel()
    _uiState.value = UiState.Ready
    _uiEvent.value = UiEvent.MoveCamera(latLngModel, zoom)
    productsListViewModel.setProducts(placeDto)
    this.placeDto = placeDto
  }

  override fun onModalBottomSheetStateNotVisible() {
    if (_uiState.value == UiState.Editing) {
      _uiState.value = UiState.Ready
    }
  }

  override fun onProductCardClick(productCardModel: ProductCardModel) {
    registerExpenseUseCase.registerExpense(
      productCardModelMapper.map(productCardModel, calendar.value)
    ).baseSubscribe {}
  }

  override fun onProductCardLongClick(productCardModel: ProductCardModel) {
    setEditProductViewModel(productCardModel)

    _uiEvent.value = UiEvent.OpenEditMode()
    _uiState.value = UiState.Editing
  }

  private fun setEditProductViewModel(productCardModel: ProductCardModel) {
    val placeProductDto = getPlaceProduct(productCardModel.id)
    editPlaceProductViewModel.placeId.value = placeDto.id
    editPlaceProductViewModel.placeProductId.value = placeProductDto.id
    editPlaceProductViewModel.description.value = placeProductDto.productDto.description
    editPlaceProductViewModel.categoryModel.value = placeProductDto.category.toViewmodel()
    editPlaceProductViewModel.price.value = placeProductDto.price.toString()
    editPlaceProductViewModel.iconUrl.value = placeProductDto.productDto.iconUrl
    editPlaceProductViewModel.variableAmount.value = placeProductDto.productDto.variableAmount
    editPlaceProductViewModel.onFinishEditAction.value =
      HomeOnFinishEditAction { _uiEvent.value = UiEvent.CloseEditMode() }
  }

  private fun getPlaceProduct(productId: Long): PlaceProductDto {
    return placeDto.products.first { it.id == productId }
  }

  private fun newProductsListViewModel() = ProductsListViewModel(
    ProductCardViewModelFactoryImpl(this), productCardModelMapper
  )
}

sealed class UiEvent {
  class MoveCamera(val latLngModel: LatLngModel, val zoom: Float) : UiEvent()
  class OpenEditMode : UiEvent()
  class CloseEditMode : UiEvent()
}

sealed class UiState {
  object Loading : UiState()
  object Ready : UiState()
  object Editing : UiState()
}
