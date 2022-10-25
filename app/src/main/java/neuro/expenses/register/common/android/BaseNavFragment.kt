package neuro.expenses.register.common.android

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment

open class BaseNavFragment : Fragment() {
  protected fun navigateTo(directions: NavDirections) {
    NavHostFragment.findNavController(this).navigate(directions)
  }

  protected fun navigateUp() {
    NavHostFragment.findNavController(this).navigateUp()
  }
}