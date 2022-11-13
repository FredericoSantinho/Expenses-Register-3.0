package neuro.expenses.register.ui.permissions

import androidx.annotation.LayoutRes
import neuro.expenses.register.R
import neuro.expenses.register.common.fragment.BaseFragment

class PermissionsFragment : BaseFragment() {

  @LayoutRes
  override fun getLayout(): Int {
    return R.layout.fragment_permissions
  }

  override fun setupViewModel() {}
}