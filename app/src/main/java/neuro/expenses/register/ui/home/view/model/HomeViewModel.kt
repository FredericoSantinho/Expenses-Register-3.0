package neuro.expenses.register.ui.home.view.model

import androidx.compose.runtime.mutableStateOf
import com.exchangebot.common.schedulers.SchedulerProvider
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import neuro.expenses.register.common.view.model.BaseViewModel
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.usecase.location.GetCurrentLocationUseCase
import neuro.expenses.register.domain.usecase.place.GetNearestPlacesUseCase
import neuro.expenses.register.ui.common.mapper.LatLngMapper

private val lisbon = LatLng(38.722252, -9.139337)

class HomeViewModel(
  val productsListViewModel: ProductsListViewModel,
  private val getNearestPlacesUseCase: GetNearestPlacesUseCase,
  private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
  private val latLngMapper: LatLngMapper,
  schedulerProvider: SchedulerProvider,
  private val nearestPlacesLimit: Int = 5,
  private val zoom: Float = 19.0f
) : BaseViewModel(schedulerProvider) {
  val cameraPosition = mutableStateOf(CameraPosition.fromLatLngZoom(lisbon, 7.0f))
  val places = mutableStateOf(emptyList<String>())
  val place = mutableStateOf(emptyPlaceDto())

  val billViewModel = BillViewModel()

  init {
    getCurrentLocationUseCase.getCurrentLocation()
      .flatMap { getNearestPlacesUseCase.getNearestPlaces(it, nearestPlacesLimit) }
      .baseSubscribe { nearestPlaces ->
        places.value = nearestPlaces.map { placeDto -> placeDto.name }
        if (nearestPlaces.isNotEmpty()) place.value = nearestPlaces.get(0)
        cameraPosition.value =
          CameraPosition.fromLatLngZoom(latLngMapper.map(place.value.latLng), zoom)
      }
  }

  private fun emptyPlaceDto(): PlaceDto {
    return PlaceDto("", LatLngDto(0.0, 0.0), emptyList())
  }
}
