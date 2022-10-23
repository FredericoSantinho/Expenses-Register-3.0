package neuro.expenses.register.ui.detailed

import neuro.expenses.register.R
import neuro.expenses.register.common.android.BaseFragment
import neuro.expenses.register.common.viewBinding
import neuro.expenses.register.databinding.FragmentDetailedBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailedFragment : BaseFragment() {

  val binding by viewBinding(FragmentDetailedBinding::bind)
  val detailedViewModel: DetailedViewModel by viewModel()

  override fun getLayout(): Int = R.layout.fragment_detailed

  override fun setupViewModel() {
    detailedViewModel.text.observe(viewLifecycleOwner) {
      binding.textDetailed.text = it
    }
  }
}