package neuro.expenses.register.ui.common.bill

import com.exchangebot.common.schedulers.SchedulerProvider
import io.reactivex.rxjava3.disposables.Disposable
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.usecase.bill.ObserveLastBillUseCase
import neuro.expenses.register.ui.common.bill.mapper.DateTimeMapper
import neuro.expenses.register.ui.common.formatter.DoubleFormatter
import neuro.expenses.register.ui.home.view.model.BillViewModel


class FeedLastBillViewModelImpl(
  private val observeLastBillUseCase: ObserveLastBillUseCase,
  private val billViewModel: BillViewModel,
  private val dateTimeMapper: DateTimeMapper,
  private val doubleFormatter: DoubleFormatter,
  private val schedulerProvider: SchedulerProvider,
  private val currency: Char = '€'
) : FeedLastBillViewModel {
  override fun subscribe(): Disposable {
    return observeLastBillUseCase.observeLastBill()
      .subscribeOn(schedulerProvider.io())
      .observeOn(schedulerProvider.ui())
      .doOnNext { publish(it) }
      .subscribe { }
  }

  private fun publish(billDto: BillDto) {
    billViewModel.iconUrl.value = billDto.iconUrl
    billViewModel.place.value = billDto.place
    billViewModel.time.value = dateTimeMapper.mapTime(billDto.calendar)
    billViewModel.date.value = dateTimeMapper.mapDate(billDto.calendar)
    billViewModel.total.value = doubleFormatter.format(billDto.total) + " $currency"
    billViewModel.isBillOpen.value = billDto.isOpen
  }
}