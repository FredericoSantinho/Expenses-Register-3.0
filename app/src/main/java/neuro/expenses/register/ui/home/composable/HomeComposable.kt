package neuro.expenses.register.ui.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import com.exchangebot.ui.theme.ExpensesRegisterTheme
import com.google.android.gms.maps.model.LatLng
import neuro.expenses.register.ui.composables.datetime.DateTimeComposable

@Composable
fun HomeComposable(fragmentActivity: FragmentActivity) {
  val latLng = remember { mutableStateOf(LatLng(37.091495, -8.2475677)) }

  Column {
    MapsComposable(latLng, zoom = 19.0f)
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
      DateTimeComposable(fragmentActivity = fragmentActivity)
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
