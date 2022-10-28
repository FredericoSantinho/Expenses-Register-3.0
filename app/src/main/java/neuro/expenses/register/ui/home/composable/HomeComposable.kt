package neuro.expenses.register.ui.home.composable

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.exchangebot.ui.theme.ExpensesRegisterTheme
import com.google.android.gms.maps.model.LatLng
import neuro.expenses.register.R
import neuro.expenses.register.ui.composable.DropDownMenu
import neuro.expenses.register.ui.composable.MapsComposable
import neuro.expenses.register.ui.composables.datetime.DateTimeComposable

@Composable
fun HomeComposable(fragmentActivity: FragmentActivity) {
  val latLng = remember { mutableStateOf(LatLng(37.091495, -8.2475677)) }
  val places = remember { mutableStateOf(listOf("Bitoque")) }

  Column {
    MapsComposable(latLng, zoom = 19.0f)
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
      DateTimeComposable(fragmentActivity = fragmentActivity)
      DropDownMenu(
        modifier = Modifier
          .padding(start = 8.dp)
          .requiredWidth(180.dp),
        label = stringResource(id = R.string.home_place),
        listItems = places
      )
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
