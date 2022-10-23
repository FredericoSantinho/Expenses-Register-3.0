package neuro.expenses.register.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import neuro.expenses.register.common.viewBinding
import neuro.expenses.register.databinding.FragmentEditCategoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditCategoryFragment : Fragment() {

  val binding by viewBinding(FragmentEditCategoryBinding::bind)
  val editCategoryViewModel: EditCategoryViewModel by viewModel()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val root: View = binding.root

    return root
  }
}