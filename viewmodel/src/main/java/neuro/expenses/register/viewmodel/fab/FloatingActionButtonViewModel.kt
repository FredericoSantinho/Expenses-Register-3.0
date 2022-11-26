package neuro.expenses.register.viewmodel.fab

class FloatingActionButtonViewModel(private val onClick: () -> Unit = {}) {
  fun onClick() {
    onClick.invoke()
  }
}