package neuro.expenses.register.common.android.activity

import android.os.Bundle
import android.view.View

abstract class BaseActivity : BaseNavActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(getView())
    setupViewModel()
  }

  protected abstract fun getView(): View
  protected open fun setupViewModel() {}
}