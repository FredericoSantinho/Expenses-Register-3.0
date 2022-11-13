package neuro.expenses.register.common.activity

import android.os.Bundle
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : BaseNavActivity() {

  protected lateinit var binding: T

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = getViewBinding()
    setContentView(binding.root)
    setupViewModel()
  }

  protected abstract fun getViewBinding(): T
  protected open fun setupViewModel() {}
}