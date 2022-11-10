package neuro.expenses.register.ui.splash

import androidx.annotation.LayoutRes
import neuro.expenses.register.R
import neuro.expenses.register.common.android.fragment.BaseFragment

class SplashFragment : BaseFragment() {

  @LayoutRes
  override fun getLayout(): Int {
    return R.layout.fragment_splash
  }

  override fun setupViewModel() {}
}