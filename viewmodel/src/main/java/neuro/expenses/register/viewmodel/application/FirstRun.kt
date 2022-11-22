package neuro.expenses.register.viewmodel.application

interface FirstRun {
  fun isFirstRun(): Boolean
  fun setFirstRun(firstRun: Boolean)
}