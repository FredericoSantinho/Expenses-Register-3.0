package neuro.expenses.register.common.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

abstract class BaseFragment : BaseNavFragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    setupViewModel()
    return inflater.inflate(getLayout(), container, false)
  }

  @LayoutRes
  protected abstract fun getLayout(): Int
  protected abstract fun setupViewModel()
}