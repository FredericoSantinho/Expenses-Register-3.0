package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.mutableStateOf
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.service.location.NoLocationException
import neuro.expenses.register.domain.service.location.NoLocationPermissionException
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCase
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseUseCase
import neuro.expenses.register.domain.usecase.location.GetCurrentLocationUseCase
import neuro.expenses.register.domain.usecase.place.ObserveNearestPlacesUseCase
import neuro.expenses.register.domain.usecase.place.SortPlaceProducts
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.appbar.SearchHint
import neuro.expenses.register.viewmodel.appbar.Title
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.edit.placeproduct.EditPlaceProductViewModel
import neuro.expenses.register.viewmodel.home.mapper.PlaceProductCardModelMapper
import neuro.expenses.register.viewmodel.home.mapper.SearchSuggestionModelMapper
import neuro.expenses.register.viewmodel.home.mapper.toViewModel
import neuro.expenses.register.viewmodel.home.model.CameraPositionModel
import neuro.expenses.register.viewmodel.home.model.LatLngModel
import neuro.expenses.register.viewmodel.home.model.PlaceProductCardModel
import neuro.expenses.register.viewmodel.model.search.SearchSuggestionModel
import neuro.expenses.register.viewmodel.scaffold.ScaffoldViewModelState

private val lisbon = CameraPositionModel(LatLngModel(38.722252, -9.139337), 7.0f)
private val zero = LatLngDto(0.0, 0.0)

class HomeViewModel(
  private val observeNearestPlacesUseCase: ObserveNearestPlacesUseCase,
  private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
  private val getCalendarUseCase: GetCalendarUseCase,
  private val registerExpenseUseCase: RegisterExpenseUseCase,
  private val sortPlaceProducts: SortPlaceProducts,
  private val placeProductCardModelMapper: PlaceProductCardModelMapper,
  private val searchSuggestionModelMapper: SearchSuggestionModelMapper,
  override val billViewModel: BillViewModel,
  override val editPlaceProductViewModel: EditPlaceProductViewModel,
  private val scaffoldViewModelState: ScaffoldViewModelState,
  schedulerProvider: SchedulerProvider,
  private val zoom: Float = 19.0f,
  val initialCameraPosition: CameraPositionModel = lisbon,
  val title: String = "Home"
) : BaseViewModel(schedulerProvider), IHomeViewModel {
  val appBarViewModel: AppBarViewModel = AppBarViewModel()

  private val places = mutableStateOf(emptyList<PlaceDto>())
  override val placesNames = mutableStateOf(emptyList<String>())

  override val calendar = mutableStateOf(getCalendarUseCase.getCalendar())
  override val productsListViewModel: ProductsListViewModel = newProductsListViewModel()
  private lateinit var placeDto: PlaceDto

  val selectedPlaceIndex = mutableStateOf(0)
  val selectedPlaceName = mutableStateOf("")

  private val _uiState = HomeUiState()
  override val uiState = _uiState.uiState
  private val _uiEvent = HomeUiEvent()
  val uiEvent = _uiEvent.uiEvent

  init {
    appBarViewModel.title.value = HomeTitle
    appBarViewModel.query.filter { this::placeDto.isInitialized }.flatMapSingle { query ->
      sortPlaceProducts.sortPlaceProducts(placeDto.products).map { query }
    }.baseSubscribe { query ->
      productsListViewModel.setProducts(placeDto, query)
    }
    getCurrentLocationUseCase.getCurrentLocation()
      .baseSubscribe(onSuccess = {}, onError = ::handleLocationPermissionException)
  }

  override fun onComposition() {
    scaffoldViewModelState.reset()
    scaffoldViewModelState.appBarViewModel.value = appBarViewModel
  }

  override fun onSelectedPlace(index: Int) {
    onSelectedPlace(places.value.get(index))
  }

  fun onProductCardClick(placeProductCardModel: PlaceProductCardModel) {
    registerExpenseUseCase.registerExpense(
      placeProductCardModelMapper.map(placeProductCardModel, calendar.value)
    ).baseSubscribe {}
  }

  fun onProductCardLongClick(placeProductCardModel: PlaceProductCardModel) {
    val placeProductDto = getPlaceProduct(placeProductCardModel.id)
    editPlaceProductViewModel.set(placeDto, placeProductDto) { onFinishEditPlaceProductAction() }

    _uiEvent.openEditPlaceProduct()
  }

  override fun eventConsumed() {
    _uiEvent.eventConsumed()
  }

  override fun onRequestLocationPermission() {
    _uiEvent.requestLocationPermission()
  }

  override fun onPermissionsGranted() {
    observeNearPlaces()
  }

  override fun onDismissLocationPermissionDialog() {
    observeNearPlaces(true)
  }

  private fun handleLocationPermissionException(it: Throwable) {
    if (it is NoLocationPermissionException) {
      _uiState.showLocationPermissionDialog()
    } else {
      if (!(it is NoLocationException)) {
        // Not supposed to happen
        throw it
      }
    }
  }

  private fun observeNearPlaces(defaultNullIsland: Boolean = false) {
    getCurrentLocationUseCase.getCurrentLocation().onErrorResumeNext {
      if (it is NoLocationException || (defaultNullIsland && it is NoLocationPermissionException)) {
        Single.just(zero)
      } else {
        Single.error(it)
      }
    }.flatMapObservable {
      observeNearestPlacesUseCase.observeNearestPlaces(it, Int.MAX_VALUE)
    }.baseSubscribe(onSuccess = { nearestPlaces ->
      places.value = nearestPlaces
      placesNames.value = nearestPlaces.map { placeDto -> placeDto.name }
      if (nearestPlaces.isNotEmpty()) {
        onSelectedPlace(nearestPlaces.get(selectedPlaceIndex.value))
        appBarViewModel.searchHint.value = searchProductsAndPlaces
        appBarViewModel.enableSearch()
        setupSearchSuggestions()
      } else {
        _uiState.ready()
      }
    }, onError = {
      // Not supposed to happen
      throw it
    })
  }

  private fun onSelectedPlace(placeDto: PlaceDto) {
    val latLngModel = placeDto.latLngDto.toViewModel()
    _uiEvent.moveCamera(latLngModel, zoom)
    this.placeDto = placeDto
    selectedPlaceName.value = placeDto.name
    sortPlaceProducts.sortPlaceProducts(placeDto.products)
      .flatMap { appBarViewModel.query.firstOrError() }.baseSubscribe { query ->
        productsListViewModel.setProducts(placeDto, query)
        _uiState.ready()
      }
  }

  private fun setupSearchSuggestions() {
    val productSearchSuggestionModels =
      placeDto.products.map { searchSuggestionModelMapper.map(it) }
    val placeSearchSuggestionModels =
      places.value.map { searchSuggestionModelMapper.map(it, ::onPlaceSearchSuggestion) }
    val list = mutableListOf<SearchSuggestionModel>()
    list.addAll(placeSearchSuggestionModels)
    list.addAll(productSearchSuggestionModels)
    appBarViewModel.dataIn.value = list
  }

  private fun onPlaceSearchSuggestion(placeId: Long) {
    appBarViewModel.clearSearch()
    onSelectedPlace(places.value.first { it.id == placeId })
  }

  private fun onFinishEditPlaceProductAction() {
    _uiEvent.closeEditPlaceProduct()
  }

  private fun getPlaceProduct(productId: Long): PlaceProductDto {
    return placeDto.products.first { it.id == productId }
  }

  private fun newProductsListViewModel() = ProductsListViewModel(
    placeProductCardModelMapper, ::onProductCardClick, ::onProductCardLongClick
  )
}

object searchProductsAndPlaces : SearchHint()
object HomeTitle : Title()