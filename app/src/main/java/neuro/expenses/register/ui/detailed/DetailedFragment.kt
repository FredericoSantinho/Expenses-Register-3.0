package neuro.expenses.register.ui.detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import neuro.expenses.register.common.viewBinding
import neuro.expenses.register.databinding.FragmentDetailedBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailedFragment : Fragment() {

  val binding by viewBinding(FragmentDetailedBinding::bind)
  val detailedViewModel: DetailedViewModel by viewModel()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val root: View = binding.root

    val textView: TextView = binding.textDetailed
    detailedViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }
    return root
  }
}