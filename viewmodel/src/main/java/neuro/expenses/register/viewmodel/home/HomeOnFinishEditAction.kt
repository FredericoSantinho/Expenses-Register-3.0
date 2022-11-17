package neuro.expenses.register.viewmodel.home

import neuro.expenses.register.viewmodel.edit.placeproduct.OnFinishEditAction

class HomeOnFinishEditAction(val callback: () -> Unit) : OnFinishEditAction {
  override fun onFinishEditAction() {
    callback()
  }
}