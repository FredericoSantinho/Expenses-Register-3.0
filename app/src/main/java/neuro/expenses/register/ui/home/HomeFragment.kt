package neuro.expenses.register.ui.home

import androidx.compose.runtime.Composable
import neuro.expenses.register.common.android.fragment.BaseComposeFragment
import neuro.expenses.register.ui.home.composable.HomeComposable
import neuro.expenses.register.ui.home.view.model.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseComposeFragment() {

  val homeViewModel: HomeViewModel by viewModel()

  @Composable
  override fun getComposable() {
    return HomeComposable(requireActivity())
  }
}