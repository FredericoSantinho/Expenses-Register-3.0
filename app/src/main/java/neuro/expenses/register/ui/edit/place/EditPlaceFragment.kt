package neuro.expenses.register.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import neuro.expenses.register.common.viewBinding
import neuro.expenses.register.databinding.FragmentEditPlaceBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditPlaceFragment : Fragment() {

  val binding by viewBinding(FragmentEditPlaceBinding::bind)
  val editPlaceViewModel: EditPlaceViewModel by viewModel()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val root: View = binding.root

    return root
  }
}