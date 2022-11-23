package neuro.expenses.register.ui.home.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.CoroutineScope
import neuro.expenses.register.*
import neuro.expenses.register.R
import neuro.expenses.register.common.compose.rememberUnit
import neuro.expenses.register.ui.bill.BillComposableContainer
import neuro.expenses.register.ui.common.composables.datetime.DateTimeComposable
import neuro.expenses.register.ui.common.composables.dropdown.DropDownTextField
import neuro.expenses.register.ui.common.composables.maps.MapsComposable
import neuro.expenses.register.ui.common.composables.maps.MapsMoveCameraEvent
import neuro.expenses.register.ui.edit.placeproduct.EditPlaceProductComposable
import neuro.expenses.register.ui.home.mapper.HomeMapsEventMapper
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.home.HomeViewModel
import neuro.expenses.register.viewmodel.home.IHomeViewModel
import neuro.expenses.register.viewmodel.home.UiEvent
import neuro.expenses.register.viewmodel.home.UiState
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeComposable(
  fragmentActivity: FragmentActivity,
  mapsEventMapper: HomeMapsEventMapper = get(),
  homeViewModel: HomeViewModel = getViewModel()
) {
  rememberUnit { homeViewModel.onComposition() }

  val uiState by homeViewModel.uiState
  val uiEvent = homeViewModel.uiEvent

  val loading = remember { mutableStateOf(true) }
  val moveCamera = remember { mutableStateOf<MapsMoveCameraEvent?>(null) }

  val coroutineScope = rememberCoroutineScope()
  val modalBottomSheetState = rememberModalBottomSheetState()

  ModalBottomSheetLayout(modalBottomSheetState,
    { EditPlaceProductComposable(homeViewModel.editPlaceProductViewModel) }) {
    ConstraintLayout(
      modifier = Modifier.fillMaxSize()
    ) {
      val (mainC, billC) = createRefs()

      BillComposableContainer(homeViewModel.billViewModel, Modifier.constrainAs(billC) {
        bottom.linkTo(parent.bottom)
      })
      Column(modifier = Modifier.constrainAs(mainC) {
        linkTo(top = parent.top, bottom = billC.top)
        height = Dimension.fillToConstraints
      }) {
        MapsComposable(homeViewModel.initialCameraPosition, moveCamera)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
          DateTimeComposable(
            fragmentActivity = fragmentActivity,
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
            value = homeViewModel.selectedPlace
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
    mapsEventMapper
  )

  addBackHandler(modalBottomSheetState, coroutineScope)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun onUiEvent(
  uiEvent: State<UiEvent?>,
  coroutineScope: CoroutineScope,
  modalBottomSheetState: ModalBottomSheetState,
  moveCamera: MutableState<MapsMoveCameraEvent?>,
  homeViewModel: HomeViewModel,
  mapsEventMapper: HomeMapsEventMapper
) {
  when (uiEvent.value) {
    is UiEvent.OpenEditMode -> showModalBottomSheet(
      uiEvent.value as UiEvent.OpenEditMode, coroutineScope, modalBottomSheetState
    )
    is UiEvent.CloseEditMode -> hideModalBottomSheet(
      uiEvent.value, coroutineScope, modalBottomSheetState
    )
    is UiEvent.MoveCamera -> {
      moveCamera.value = mapsEventMapper.map(uiEvent.value)
    }
    null -> {}
  }
  homeViewModel.eventConsumed()
}

@Composable
private fun onUiState(
  uiState: UiState, homeViewModel: IHomeViewModel, loading: MutableState<Boolean>
) {
  if (uiState is UiState.Loading) {
    onUiLoading()
  } else {
    onUiReady(homeViewModel, loading)
  }
}

@Composable
fun onUiLoading() {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxSize()
  ) {
    CircularProgressIndicator(modifier = Modifier.size(128.dp), strokeWidth = 4.dp)
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
  val fragmentActivity = FragmentActivity()
  ExpensesRegisterTheme {
    HomeComposable(fragmentActivity)
  }
}
