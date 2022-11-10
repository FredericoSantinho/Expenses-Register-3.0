package neuro.expenses.register.ui.home

import androidx.compose.runtime.Composable
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import neuro.expenses.register.common.android.fragment.BaseComposeFragment
import neuro.expenses.register.ui.home.composable.HomeComposable
import neuro.expenses.register.viewmodel.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private val lisbon = CameraPosition.fromLatLngZoom(LatLng(38.722252, -9.139337), 7.0f)

class HomeFragment : BaseComposeFragment() {

  val homeViewModel: HomeViewModel by viewModel()

  @Composable
  override fun getComposable() {
    return HomeComposable(requireActivity(), lisbon)
  }
}