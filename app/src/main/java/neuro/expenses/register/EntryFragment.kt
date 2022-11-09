package neuro.expenses.register

import androidx.annotation.LayoutRes
import neuro.expenses.register.common.android.fragment.BaseFragment

class EntryFragment : BaseFragment() {

  @LayoutRes
  override fun getLayout(): Int {
    return R.layout.fragment_entry
  }

  override fun setupViewModel() {}
}