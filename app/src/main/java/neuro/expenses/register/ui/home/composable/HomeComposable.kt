package neuro.expenses.register.ui.home.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
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
import androidx.fragment.app.FragmentActivity
import com.exchangebot.ui.theme.ExpensesRegisterTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import neuro.expenses.register.R
import neuro.expenses.register.ui.common.bill.BillComposableContainer
import neuro.expenses.register.ui.composable.DropDownTextField
import neuro.expenses.register.ui.composable.MapsComposable
import neuro.expenses.register.ui.composables.datetime.DateTimeComposable
import neuro.expenses.register.ui.home.viewmodel.HomeViewModel
import neuro.expenses.register.ui.home.viewmodel.UiState
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeComposable(
  fragmentActivity: FragmentActivity,
  initialCameraPosition: CameraPosition,
  homeViewModel: HomeViewModel = getViewModel()
) {
  val uiState by homeViewModel.uiState

  val loading = remember { mutableStateOf(true) }
  val cameraPosition: MutableState<CameraPosition> =
    remember { mutableStateOf(initialCameraPosition) }

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
      MapsComposable(cameraPosition)
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
      onUiState(uiState, homeViewModel, loading, cameraPosition)
    }
  }
}

@Composable
private fun onUiState(
  uiState: UiState,
  homeViewModel: HomeViewModel,
  loading: MutableState<Boolean>,
  cameraPosition: MutableState<CameraPosition>
) {
  when (uiState) {
    is UiState.Loading -> onUiLoading()
    is UiState.Ready -> onUiReady(uiState, homeViewModel, loading, cameraPosition)
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
private fun onUiReady(
  uiState: UiState.Ready,
  homeViewModel: HomeViewModel,
  loading: MutableState<Boolean>,
  cameraPosition: MutableState<CameraPosition>
) {
  loading.value = false
  cameraPosition.value =
    CameraPosition.fromLatLngZoom(uiState.latLng, uiState.zoom)
  ProductsListComposable(homeViewModel.productsListViewModel)
}

@Preview
@Composable
fun PreviewHomeComposable() {
  ExpensesRegisterTheme {
    HomeComposable(
      FragmentActivity(),
      CameraPosition.fromLatLngZoom(LatLng(38.722252, -9.139337), 7.0f)
    )
  }
}
