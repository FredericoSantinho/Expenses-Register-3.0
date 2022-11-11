package neuro.expenses.register.common.schedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class SchedulerProviderImpl : SchedulerProvider {
  override fun io(): Scheduler = Schedulers.io()
  override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}