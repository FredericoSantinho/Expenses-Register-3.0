package neuro.expenses.register.ui.home.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.FragmentActivity
import neuro.expenses.register.R
import neuro.expenses.register.ui.bill.BillComposableContainer
import neuro.expenses.register.ui.common.composables.datetime.DateTimeComposable
import neuro.expenses.register.ui.common.composables.dropdown.DropDownTextField
import neuro.expenses.register.ui.common.composables.maps.MapsComposable
import neuro.expenses.register.ui.common.composables.maps.mapper.CameraPositionViewMapper
import neuro.expenses.register.ui.home.mapper.HomeMapsEventMapper
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.home.HomeViewModel
import neuro.expenses.register.viewmodel.home.IHomeViewModel
import neuro.expenses.register.viewmodel.home.UiState
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeComposable(
  fragmentActivity: FragmentActivity,
  mapsEventMapper: HomeMapsEventMapper = get(),
  cameraPositionViewMapper: CameraPositionViewMapper = get(),
  homeViewModel: HomeViewModel = getViewModel()
) {
  val uiState by homeViewModel.uiState
  val uiEvent = homeViewModel.uiEvent.observeAsState(null)

  val loading = remember { mutableStateOf(true) }

  ConstraintLayout(
    modifier = Modifier
      .fillMaxWidth()
  ) {
    val (mainC, billC) = createRefs()

    BillComposableContainer(homeViewModel.billViewModel, Modifier.constrainAs(billC) {
      bottom.linkTo(parent.bottom)
    })
    Column(modifier = Modifier.constrainAs(mainC) {
      top.linkTo(parent.top)
      bottom.linkTo(billC.top)
      height = Dimension.fillToConstraints
    }) {
      MapsComposable(
        homeViewModel.initialCameraPosition,
        cameraPositionViewMapper,
        mapsEventMapper.map(uiEvent.value)
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
          onSelectedOption = { homeViewModel.onSelectedPlace(it) }
        )
      }
      Divider(thickness = 1.dp, color = Color.LightGray)

      onUiState(uiState, homeViewModel, loading)
    }
  }
}

@Composable
private fun onUiState(
  uiState: UiState,
  homeViewModel: IHomeViewModel,
  loading: MutableState<Boolean>
) {
  when (uiState) {
    is UiState.Loading -> onUiLoading()
    is UiState.Ready -> onUiReady(homeViewModel, loading)
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
  ExpensesRegisterTheme {
    HomeComposable(
      FragmentActivity()
    )
  }
}
