package neuro.expenses.register.common.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment

abstract class BaseFragment : Fragment() {

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

  protected fun navigateTo(directions: NavDirections) {
    NavHostFragment.findNavController(this).navigate(directions)
  }

  protected fun navigateUp() {
    NavHostFragment.findNavController(this).navigateUp()
  }
}