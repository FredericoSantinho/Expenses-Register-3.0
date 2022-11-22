package neuro.expenses.register.viewmodel.application

import neuro.expenses.register.domain.usecase.populate.PrePopulate
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider

class ApplicationViewModel(
  private val schedulerProvider: SchedulerProvider,
  private val prePopulate: PrePopulate,
  private val firstRun: FirstRun
) {
  fun onCreate() {
    if (firstRun.isFirstRun()) {
      prePopulate.prePopulate().subscribeOn(schedulerProvider.io()).subscribe()
    }

    firstRun.setFirstRun(false)
  }
}