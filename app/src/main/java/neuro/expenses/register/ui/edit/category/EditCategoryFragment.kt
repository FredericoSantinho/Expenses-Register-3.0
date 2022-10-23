package neuro.expenses.register.ui.home

import neuro.expenses.register.R
import neuro.expenses.register.common.android.BaseFragment
import neuro.expenses.register.common.viewBinding
import neuro.expenses.register.databinding.FragmentEditCategoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditCategoryFragment : BaseFragment() {

  val binding by viewBinding(FragmentEditCategoryBinding::bind)
  val editCategoryViewModel: EditCategoryViewModel by viewModel()

  override fun getLayout(): Int = R.layout.fragment_edit_category

  override fun setupViewModel() {

  }
}