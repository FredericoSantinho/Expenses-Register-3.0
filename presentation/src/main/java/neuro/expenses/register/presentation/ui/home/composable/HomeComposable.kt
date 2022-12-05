package neuro.expenses.register.presentation.ui.home.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.CoroutineScope
import neuro.expenses.register.*
import neuro.expenses.register.presentation.R
import neuro.expenses.register.presentation.common.alert.AlertDialog
import neuro.expenses.register.presentation.common.compose.rememberSaveableUnit
import neuro.expenses.register.presentation.ui.bill.BillCardComposableContainer
import neuro.expenses.register.presentation.ui.common.composables.datetime.DateTimeComposable
import neuro.expenses.register.presentation.ui.common.composables.dropdown.DropDownTextField
import neuro.expenses.register.presentation.ui.common.composables.maps.MapsComposable
import neuro.expenses.register.presentation.ui.common.composables.maps.MapsMoveCameraEvent
import neuro.expenses.register.presentation.ui.common.composables.modal.*
import neuro.expenses.register.presentation.ui.edit.placeproduct.EditPlaceProductComposable
import neuro.expenses.register.presentation.ui.home.mapper.HomeMapsUiEventMapper
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.home.HomeUiEvent.UiEvent
import neuro.expenses.register.viewmodel.home.HomeUiState.UiState
import neuro.expenses.register.viewmodel.home.HomeViewModel
import neuro.expenses.register.viewmodel.home.IHomeViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterialApi::class)
@Composable
fun HomeComposable(
  navController: NavHostController? = null,
  mapsEventMapper: HomeMapsUiEventMapper = get(),
  homeViewModel: HomeViewModel = getViewModel()
) {
  rememberSaveableUnit { homeViewModel.onComposition() }

  val uiState by homeViewModel.uiState
  val uiEvent = homeViewModel.uiEvent

  val loading = remember { mutableStateOf(true) }
  val moveCamera = remember { mutableStateOf<MapsMoveCameraEvent?>(null) }

  val coroutineScope = rememberCoroutineScope()
  val modalBottomSheetState = rememberModalBottomSheetState()

  val locationPermissionsState = rememberMultiplePermissionsState(
    listOf(
      android.Manifest.permission.ACCESS_COARSE_LOCATION,
      android.Manifest.permission.ACCESS_FINE_LOCATION,
    )
  )

  ModalBottomSheetLayout(
    modalBottomSheetState,
    modalContent = { EditPlaceProductComposable(homeViewModel.editPlaceProductViewModel) }) {
    ConstraintLayout(
      modifier = Modifier.fillMaxSize()
    ) {
      val (mainC, billC) = createRefs()

      BillCardComposableContainer(homeViewModel.billCardViewModel, Modifier.constrainAs(billC) {
        bottom.linkTo(parent.bottom)
      })
      Column(modifier = Modifier.constrainAs(mainC) {
        linkTo(top = parent.top, bottom = billC.top)
        height = Dimension.fillToConstraints
      }) {
        MapsComposable(
          homeViewModel.cameraPosition,
          moveCamera,
          Modifier.height(208.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
          DateTimeComposable(
            modifier = Modifier
              .align(CenterVertically)
              .padding(start = 8.dp),
            calendar = homeViewModel.calendar
          )
          DropDownTextField(
            modifier = Modifier
              .padding(start = 8.dp)
              .requiredWidth(180.dp),
            label = stringResource(id = R.string.home_place),
            listItems = homeViewModel.placesNames,
            onSelectedOption = { homeViewModel.onSelectedPlace(it) },
            selectedItemIndex = homeViewModel.selectedPlaceIndex,
            value = homeViewModel.selectedPlaceName
          )
        }
        Divider(thickness = 1.dp, color = Color.LightGray)

        onUiState(uiState, homeViewModel, loading)
      }
    }
  }
  onUiEvent(
    uiEvent,
    coroutineScope,
    modalBottomSheetState,
    moveCamera,
    homeViewModel,
    mapsEventMapper,
    locationPermissionsState
  )

  navController?.let { addBackHandler(modalBottomSheetState, coroutineScope, navController) }

  handleLocationPermissions(locationPermissionsState, homeViewModel)
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun handleLocationPermissions(
  locationPermissionsState: MultiplePermissionsState, homeViewModel: HomeViewModel
) {
  if (locationPermissionsState.allPermissionsGranted) {
    rememberSaveableUnit {
      homeViewModel.onPermissionsGranted()
    }
  }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
private fun onUiEvent(
  uiEvent: State<UiEvent?>,
  coroutineScope: CoroutineScope,
  modalBottomSheetState: ModalBottomSheetState,
  moveCamera: MutableState<MapsMoveCameraEvent?>,
  homeViewModel: HomeViewModel,
  mapsEventMapper: HomeMapsUiEventMapper,
  locationPermissionsState: MultiplePermissionsState
) {
  when (uiEvent.value) {
    is UiEvent.OpenEditMode -> showModalBottomSheet(
      coroutineScope, modalBottomSheetState
    )
    is UiEvent.CloseEditMode -> hideModalBottomSheet(
      coroutineScope, modalBottomSheetState
    )
    is UiEvent.MoveCamera -> {
      moveCamera.value = mapsEventMapper.map(uiEvent.value)
    }
    is UiEvent.RequestLocationPermission -> {
      onRequestLocationPermission(uiEvent.value, locationPermissionsState)
    }
    null -> {}
  }
  homeViewModel.eventConsumed()
}

@Composable
private fun onShowLocationPermissionDialog(homeViewModel: IHomeViewModel) {
  AlertDialog(title = stringResource(R.string.permissions_title),
    text = stringResource(R.string.permissions_text),
    confirmButtonText = stringResource(R.string.permissions_grant),
    dismissButtonText = stringResource(R.string.permissions_null_island),
    onConfirmButton = { homeViewModel.onRequestLocationPermission() },
    onDismissButton = { homeViewModel.onDismissLocationPermissionDialog() })
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun onRequestLocationPermission(
  uiEvent: UiEvent?,
  locationPermissionsState: MultiplePermissionsState
) {
  LaunchedEffect(key1 = uiEvent, block = {
    locationPermissionsState.launchMultiplePermissionRequest()
  })
}

@Composable
private fun onUiState(
  uiState: UiState, homeViewModel: IHomeViewModel, loading: MutableState<Boolean>
) {
  when (uiState) {
    UiState.Ready -> onUiReady(homeViewModel, loading)
    UiState.Loading -> {
      onUiLoading(loading)
    }
    UiState.ShowLocationPermissionDialog -> {
      onShowLocationPermissionDialog(homeViewModel)
    }
  }
}

@Composable
private fun onUiLoading(loading: MutableState<Boolean>) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxSize()
  ) {
    CircularProgressIndicator(modifier = Modifier.size(128.dp), strokeWidth = 4.dp)
    loading.value = true
  }
}

@Composable
private fun onUiReady(homeViewModel: IHomeViewModel, loading: MutableState<Boolean>) {
  loading.value = false
  ProductsListComposable(homeViewModel.productsListViewModel)
}

@Preview
@Composable
fun PreviewHomeComposable() {
  ExpensesRegisterTheme {
    HomeComposable()
  }
}
