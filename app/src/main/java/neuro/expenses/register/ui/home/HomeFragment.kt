package neuro.expenses.register.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import neuro.expenses.register.R
import neuro.expenses.register.common.viewBinding
import neuro.expenses.register.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

  val binding by viewBinding(FragmentHomeBinding::bind)
  val homeViewModel: HomeViewModel by viewModel()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    homeViewModel.text.observe(viewLifecycleOwner) {
      binding.textHome.text = it
    }

    return inflater.inflate(R.layout.fragment_home, container, false)
  }

}