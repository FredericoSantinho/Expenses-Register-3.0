package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCase
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseUseCase
import neuro.expenses.register.domain.usecase.location.GetCurrentLocationUseCase
import neuro.expenses.register.domain.usecase.place.ObserveNearestPlacesUseCase
import neuro.expenses.register.domain.usecase.place.SortPlaceProducts
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.appbar.SearchHint
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.bill.FeedLastBillViewModel
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.asState
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.edit.placeproduct.EditPlaceProductViewModel
import neuro.expenses.register.viewmodel.home.factory.ProductCardViewModelFactoryImpl
import neuro.expenses.register.viewmodel.home.mapper.ProductCardModelMapper
import neuro.expenses.register.viewmodel.home.mapper.SearchSuggestionModelMapper
import neuro.expenses.register.viewmodel.home.mapper.toViewModel
import neuro.expenses.register.viewmodel.home.model.CameraPositionModel
import neuro.expenses.register.viewmodel.home.model.LatLngModel
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import neuro.expenses.register.viewmodel.main.MainViewModel
import neuro.expenses.register.viewmodel.model.search.SearchSuggestionModel

private val lisbon = CameraPositionModel(LatLngModel(38.722252, -9.139337), 7.0f)

class HomeViewModel(
  private val observeNearestPlacesUseCase: ObserveNearestPlacesUseCase,
  private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
  private val getCalendarUseCase: GetCalendarUseCase,
  private val registerExpenseUseCase: RegisterExpenseUseCase,
  private val sortPlaceProducts: SortPlaceProducts,
  private val productCardModelMapper: ProductCardModelMapper,
  private val searchSuggestionModelMapper: SearchSuggestionModelMapper,
  private val feedLastBillViewModel: FeedLastBillViewModel,
  override val billViewModel: BillViewModel,
  override val editPlaceProductViewModel: EditPlaceProductViewModel,
  private val mainViewModel: MainViewModel,
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
  val selectedPlace = mutableStateOf("")

  private val _uiState = mutableStateOf<UiState>(UiState.Loading)
  override val uiState = _uiState.asState()

  private val _uiEvent = HomeUiEvent()
  val uiEvent = _uiEvent.uiEvent

  init {
    appBarViewModel.title.value = title
    appBarViewModel.query().baseSubscribe { query ->
      productsListViewModel.setProducts(placeDto, query)
    }
    getCurrentLocationUseCase.getCurrentLocation().flatMapObservable {
      observeNearestPlacesUseCase.observeNearestPlaces(it, Int.MAX_VALUE)
    }.baseSubscribe { nearestPlaces ->
      places.value = nearestPlaces
      placesNames.value = nearestPlaces.map { placeDto -> placeDto.name }
      if (nearestPlaces.isNotEmpty()) {
        onSelectedPlace(nearestPlaces.get(selectedPlaceIndex.value))
        appBarViewModel.searchHint.value = SearchProductsAndPlaces
        appBarViewModel.enableSearch()
        setupSearchSuggestions()
      } else {
        _uiState.value = UiState.Ready
      }
    }
    feedLastBillViewModel.observe().baseSubscribe { }
  }

  override fun onComposition() {
    mainViewModel.appBarViewModel.value = appBarViewModel
  }

  override fun onSelectedPlace(index: Int) {
    onSelectedPlace(places.value.get(index))
  }

  override fun onProductCardClick(productCardModel: ProductCardModel) {
    registerExpenseUseCase.registerExpense(
      productCardModelMapper.map(productCardModel, calendar.value)
    ).baseSubscribe {}
  }

  override fun onProductCardLongClick(productCardModel: ProductCardModel) {
    setEditProductViewModel(productCardModel)

    _uiEvent.openEditPlaceProduct()
  }

  override fun eventConsumed() {
    _uiEvent.eventConsumed()
  }

  private fun onSelectedPlace(placeDto: PlaceDto) {
    val latLngModel = placeDto.latLngDto.toViewModel()
    _uiEvent.moveCamera(latLngModel, zoom)
    this.placeDto = placeDto
    selectedPlace.value = placeDto.name
    sortPlaceProducts.sortPlaceProducts(placeDto.products).baseSubscribe {
      productsListViewModel.setProducts(placeDto, appBarViewModel.query.value)
      _uiState.value = UiState.Ready
    }
  }

  private fun setupSearchSuggestions() {
    val productSearchSuggestionModels =
      placeDto.products.map { searchSuggestionModelMapper.map(it) }
    val placeSearchSuggestionModels =
      places.value.map { searchSuggestionModelMapper.map(it, { onPlaceSearchSuggestion(it) }) }
    val list = mutableListOf<SearchSuggestionModel>()
    list.addAll(placeSearchSuggestionModels)
    list.addAll(productSearchSuggestionModels)
    appBarViewModel.dataIn.value = list
  }

  private fun onPlaceSearchSuggestion(placeId: Long) {
    appBarViewModel.clearSearch()
    onSelectedPlace(places.value.first { it.id == placeId })
  }

  private fun setEditProductViewModel(productCardModel: ProductCardModel) {
    val placeProductDto = getPlaceProduct(productCardModel.id)
    editPlaceProductViewModel.placeDto.value = placeDto
    editPlaceProductViewModel.placeProductId.value = placeProductDto.id
    editPlaceProductViewModel.description.value = placeProductDto.productDto.description
    editPlaceProductViewModel.category.value = placeProductDto.category.name
    editPlaceProductViewModel.price.value = placeProductDto.price.toString()
    editPlaceProductViewModel.iconUrl.value = placeProductDto.productDto.iconUrl
    editPlaceProductViewModel.variableAmount.value = placeProductDto.productDto.variableAmount
    editPlaceProductViewModel.onFinishEditAction.value = { _uiEvent.closeEditPlaceProduct() }
  }

  private fun getPlaceProduct(productId: Long): PlaceProductDto {
    return placeDto.products.first { it.id == productId }
  }

  private fun newProductsListViewModel() = ProductsListViewModel(
    ProductCardViewModelFactoryImpl(this), productCardModelMapper
  )
}

sealed class UiState {
  object Loading : UiState()
  object Ready : UiState()
}

object SearchProductsAndPlaces : SearchHint()