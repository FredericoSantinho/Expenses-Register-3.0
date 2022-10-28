package neuro.expenses.register.ui.home

import androidx.compose.runtime.Composable
import neuro.expenses.register.common.android.BaseComposeFragment
import neuro.expenses.register.common.viewBinding
import neuro.expenses.register.databinding.FragmentHomeBinding
import neuro.expenses.register.ui.home.composable.HomeComposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseComposeFragment() {

  val binding by viewBinding(FragmentHomeBinding::bind)
  val homeViewModel: HomeViewModel by viewModel()

  @Composable
  override fun getComposable() {
    return HomeComposable(requireActivity())
  }

}