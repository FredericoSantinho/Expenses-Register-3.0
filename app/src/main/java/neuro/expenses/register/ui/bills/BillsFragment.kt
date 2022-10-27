package neuro.expenses.register.ui.bills

import neuro.expenses.register.R
import neuro.expenses.register.common.android.BaseFragment
import neuro.expenses.register.common.viewBinding
import neuro.expenses.register.databinding.FragmentEditPlaceBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BillsFragment : BaseFragment() {

  val binding by viewBinding(FragmentEditPlaceBinding::bind)
  val billsViewModel: BillsViewModel by viewModel()

  override fun getLayout(): Int = R.layout.fragment_edit_place

  override fun setupViewModel() {

  }
}