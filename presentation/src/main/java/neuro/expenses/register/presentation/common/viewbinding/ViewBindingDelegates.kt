package neuro.expenses.register.presentation.common.viewbinding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingDelegate<T : ViewBinding>(
  val fragment: Fragment,
  val viewBindingFactory: (View) -> T,
) : ReadOnlyProperty<Fragment, T> {

  private var binding: T? = null

  init {
    fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
      override fun onCreate(owner: LifecycleOwner) {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
          viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
              binding = null
            }
          })
        }
      }
    })
  }

  override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
    this.binding?.let { return it }
    val lifecycle = fragment.viewLifecycleOwner.lifecycle
    if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
      throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
    }
    return viewBindingFactory(thisRef.requireView()).also { this.binding = it }
  }
}