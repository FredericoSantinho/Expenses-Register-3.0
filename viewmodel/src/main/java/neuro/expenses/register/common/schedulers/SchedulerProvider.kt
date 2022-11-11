package neuro.expenses.register.common.schedulers

import io.reactivex.rxjava3.core.Scheduler

interface SchedulerProvider {
  fun io(): Scheduler
  fun ui(): Scheduler
}