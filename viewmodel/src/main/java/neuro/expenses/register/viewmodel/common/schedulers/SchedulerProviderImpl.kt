package neuro.expenses.register.viewmodel.common.schedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class SchedulerProviderImpl(
  private val ioScheduler: Scheduler = Schedulers.io(),
  private val uiScheduler: Scheduler = AndroidSchedulers.mainThread()
) : SchedulerProvider {
  override fun io(): Scheduler = ioScheduler
  override fun ui(): Scheduler = uiScheduler
}