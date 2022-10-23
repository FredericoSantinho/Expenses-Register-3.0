package neuro.expenses.register.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import neuro.expenses.register.common.viewBinding
import neuro.expenses.register.databinding.FragmentEditProductBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProductFragment : Fragment() {

  val binding by viewBinding(FragmentEditProductBinding::bind)
  val editProductViewModel: EditProductViewModel by viewModel()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return binding.root
  }
}