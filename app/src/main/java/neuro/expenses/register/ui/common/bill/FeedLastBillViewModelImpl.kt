package neuro.expenses.register.ui.common.bill

import com.exchangebot.common.schedulers.SchedulerProvider
import io.reactivex.rxjava3.disposables.Disposable
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.usecase.bill.ObserveLastBillUseCase
import neuro.expenses.register.ui.common.bill.mapper.BillModelMapper


class FeedLastBillViewModelImpl(
  private val observeLastBillUseCase: ObserveLastBillUseCase,
  private val billViewModel: BillViewModel,
  private val billModelMapper: BillModelMapper,
  private val schedulerProvider: SchedulerProvider
) : FeedLastBillViewModel {
  override fun subscribe(): Disposable {
    return observeLastBillUseCase.observeLastBill()
      .subscribeOn(schedulerProvider.io())
      .observeOn(schedulerProvider.ui())
      .doOnNext { publish(it) }
      .subscribe { }
  }

  private fun publish(billDto: BillDto) {
    val billModel = billModelMapper.map(billDto)
    billViewModel.setBillModel(billModel)
  }
}