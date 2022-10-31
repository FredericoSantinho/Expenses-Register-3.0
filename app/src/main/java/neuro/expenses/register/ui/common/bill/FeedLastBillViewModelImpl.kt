package neuro.expenses.register.ui.common.bill

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.usecase.bill.ObserveLastBillUseCase
import neuro.expenses.register.ui.home.view.model.BillViewModel
import neuro.expenses.register.ui.manual.register.mapper.DateTimeMapper
import neuro.expenses.register.ui.manual.register.mapper.DoubleMapper

class FeedLastBillViewModelImpl(
  private val observeLastBillUseCase: ObserveLastBillUseCase,
  private val billViewModel: BillViewModel,
  private val dateTimeMapper: DateTimeMapper,
  private val doubleMapper: DoubleMapper,
  private val currency: Char = '€'
) : FeedLastBillViewModel {
  override fun subscribe(): Disposable {
    return observeLastBillUseCase.observeLastBill()
      .observeOn(AndroidSchedulers.mainThread())
      .doOnNext { publish(it) }
      .subscribe { }
  }

  private fun publish(billDto: BillDto) {
    billViewModel.iconUrl.value = billDto.iconUrl
    billViewModel.place.value = billDto.place
    billViewModel.time.value = dateTimeMapper.mapTime(billDto.timestamp)
    billViewModel.date.value = dateTimeMapper.mapDate(billDto.timestamp)
    billViewModel.total.value = doubleMapper.map(billDto.total) + " $currency"
  }

}