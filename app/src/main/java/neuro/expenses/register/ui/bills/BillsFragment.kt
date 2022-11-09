package neuro.expenses.register.ui.bills

import androidx.compose.runtime.Composable
import neuro.expenses.register.common.android.fragment.BaseComposeFragment
import neuro.expenses.register.ui.bills.composable.BillsComposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class BillsFragment : BaseComposeFragment() {

  val billsViewModel: BillsViewModel by viewModel()

  @Composable
  override fun getComposable() {
    return BillsComposable(fragmentActivity = requireActivity())
  }
}