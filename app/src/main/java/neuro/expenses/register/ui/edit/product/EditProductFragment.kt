package neuro.expenses.register.ui.home

import neuro.expenses.register.R
import neuro.expenses.register.common.android.BaseFragment
import neuro.expenses.register.common.viewBinding
import neuro.expenses.register.databinding.FragmentEditProductBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProductFragment : BaseFragment() {

  val binding by viewBinding(FragmentEditProductBinding::bind)
  val editProductViewModel: EditProductViewModel by viewModel()

  override fun getLayout(): Int = R.layout.fragment_edit_product

  override fun setupViewModel() {

  }
}