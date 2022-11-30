package neuro.expenses.register.presentation.test

import io.reactivex.rxjava3.schedulers.TestScheduler
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider

class TestSchedulerProvider(
  private val ioScheduler: TestScheduler = TestScheduler(),
  private val uiScheduler: TestScheduler = TestScheduler()
) : SchedulerProvider {
  override fun io(): TestScheduler = ioScheduler
  override fun ui(): TestScheduler = uiScheduler
}