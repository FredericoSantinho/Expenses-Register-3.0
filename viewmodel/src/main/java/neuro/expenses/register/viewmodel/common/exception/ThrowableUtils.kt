package neuro.expenses.register.viewmodel.common.exception

import kotlin.reflect.KClass

fun Throwable.runIf(clazz: KClass<*>, action: () -> Unit) {
  if (this::class.simpleName == clazz.simpleName) {
    action()
  } else {
    throw this
  }
}