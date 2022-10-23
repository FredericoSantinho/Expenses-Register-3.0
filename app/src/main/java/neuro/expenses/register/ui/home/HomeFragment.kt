package neuro.expenses.register.ui.home

import neuro.expenses.register.R
import neuro.expenses.register.common.android.BaseFragment
import neuro.expenses.register.common.viewBinding
import neuro.expenses.register.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

  val binding by viewBinding(FragmentHomeBinding::bind)
  val homeViewModel: HomeViewModel by viewModel()

  override fun getLayout(): Int = R.layout.fragment_home

  override fun setupViewModel() {
    homeViewModel.text.observe(viewLifecycleOwner) {
      binding.textHome.text = it
    }
  }
}