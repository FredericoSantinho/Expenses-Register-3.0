package neuro.expenses.register.ui.home.composable

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import neuro.expenses.register.*
import neuro.expenses.register.R
import neuro.expenses.register.common.back.BackNavElement
import neuro.expenses.register.common.back.DefaultBackHandler
import neuro.expenses.register.common.back.FinishActivityHandler
import neuro.expenses.register.common.back.modalBackNavElement
import neuro.expenses.register.ui.bill.BillComposableContainer
import neuro.expenses.register.ui.common.composables.appbar.SearchAppBar
import neuro.expenses.register.ui.common.composables.datetime.DateTimeComposable
import neuro.expenses.register.ui.common.composables.dropdown.DropDownTextField
import neuro.expenses.register.ui.common.composables.maps.MapsComposable
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
  navController: NavHostController,
  mapsEventMapper: HomeMapsEventMapper = get(),
  homeViewModel: HomeViewModel = getViewModel()
) {
  val uiState by homeViewModel.uiState
  val uiEvent = homeViewModel.uiEvent.observeAsState(null)

  val loading = remember { mutableStateOf(true) }

  val coroutineScope = rememberCoroutineScope()
  val modalBottomSheetInitialValue = remember {
    if (uiState == UiState.Editing) ModalBottomSheetValue.Expanded else ModalBottomSheetValue.Hidden
  }
  val modalBottomSheetState = rememberModalBottomSheetState(
    modalBottomSheetInitialValue, confirmStateChange = {
      it != ModalBottomSheetValue.HalfExpanded
    }, skipHalfExpanded = true
  )

  Scaffold(topBar = { SearchAppBar(navController, fragmentActivity) }) {
    ConstraintLayout(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
    ) {
      val (mainC, billC) = createRefs()

      BillComposableContainer(homeViewModel.billViewModel, Modifier.constrainAs(billC) {
        bottom.linkTo(parent.bottom)
      })
      Column(modifier = Modifier.constrainAs(mainC) {
        linkTo(top = parent.top, bottom = billC.top)
        height = Dimension.fillToConstraints
      }) {
        MapsComposable(
          homeViewModel.initialCameraPosition, mapsEventMapper.map(uiEvent.value)
        )
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
            selectedItemIndex = homeViewModel.selectedPlaceIndex
          )
        }
        Divider(thickness = 1.dp, color = Color.LightGray)

        onUiState(uiState, homeViewModel, loading)
      }
    }
    ModalBottomSheetLayout(sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
      sheetBackgroundColor = Color.Transparent,
      sheetState = modalBottomSheetState,
      sheetContent = {
        EditPlaceProductComposable(homeViewModel.editPlaceProductViewModel)
      }) {}
    onUiEvent(uiEvent, coroutineScope, modalBottomSheetState)
  }


  if (!modalBottomSheetState.isVisible) {
    homeViewModel.onModalBottomSheetStateNotVisible()
  }

  addBackHandler(modalBottomSheetState, coroutineScope)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun addBackHandler(
  modalBottomSheetState: ModalBottomSheetState, coroutineScope: CoroutineScope
) {
  val activity = LocalContext.current as? Activity
  DefaultBackHandler(
    BackNavElement.default(
      modalBackNavElement(modalBottomSheetState, coroutineScope), FinishActivityHandler(activity)
    )
  )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun onUiEvent(
  uiEvent: State<UiEvent?>,
  coroutineScope: CoroutineScope,
  modalBottomSheetState: ModalBottomSheetState
) {
  if (uiEvent.value != null) {
    when (uiEvent.value) {
      is UiEvent.OpenEditMode -> onOpenEditMode(
        uiEvent.value as UiEvent.OpenEditMode, coroutineScope, modalBottomSheetState
      )
      is UiEvent.CloseEditMode -> onCloseEditMode(
        uiEvent.value as UiEvent.CloseEditMode, coroutineScope, modalBottomSheetState
      )
      is UiEvent.MoveCamera -> {}
      null -> {}
    }
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun onOpenEditMode(
  uiEvent: UiEvent, coroutineScope: CoroutineScope, modalBottomSheetState: ModalBottomSheetState
) {
  LaunchedEffect(uiEvent) {
    coroutineScope.launch {
      modalBottomSheetState.show()
    }
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun onCloseEditMode(
  uiEvent: UiEvent, coroutineScope: CoroutineScope, modalBottomSheetState: ModalBottomSheetState
) {
  LaunchedEffect(uiEvent) {
    coroutineScope.launch {
      modalBottomSheetState.hide()
    }
  }
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
    HomeComposable(
      fragmentActivity, NavHostController(fragmentActivity)
    )
  }
}
