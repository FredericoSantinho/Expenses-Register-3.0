package neuro.expenses.register.ui.home.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
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
import neuro.expenses.register.R
import neuro.expenses.register.ui.common.BillComposableContainer
import neuro.expenses.register.ui.composable.DropDownTextField
import neuro.expenses.register.ui.composable.MapsComposable
import neuro.expenses.register.ui.composables.datetime.DateTimeComposable
import neuro.expenses.register.ui.home.view.model.HomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeComposable(
  fragmentActivity: FragmentActivity,
  homeViewModel: HomeViewModel = getViewModel()
) {
  ConstraintLayout(
    modifier = Modifier
      .fillMaxWidth()
  ) {
    val (mainC, billC) = createRefs()

    BillComposableContainer(homeViewModel.billViewModel, modifier = Modifier.constrainAs(billC) {
      bottom.linkTo(parent.bottom)
    })
    Column(modifier = Modifier.constrainAs(mainC) {
      top.linkTo(parent.top)
      bottom.linkTo(billC.top)
      height = Dimension.fillToConstraints
    }) {
      MapsComposable(homeViewModel.latLng, zoom = 19.0f)
      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        DateTimeComposable(
          fragmentActivity = fragmentActivity,
          modifier = Modifier
            .align(CenterVertically)
            .padding(start = 8.dp)
        )
        DropDownTextField(
          modifier = Modifier
            .padding(start = 8.dp)
            .requiredWidth(180.dp),
          label = stringResource(id = R.string.home_place),
          listItems = homeViewModel.places
        )
      }
      Divider(thickness = 1.dp, color = Color.LightGray)
      ProductsListComposable(homeViewModel.productsListViewModel)
    }
  }
}

@Preview
@Composable
fun PreviewHomeComposable() {
  ExpensesRegisterTheme {
    HomeComposable(FragmentActivity())
  }
}
