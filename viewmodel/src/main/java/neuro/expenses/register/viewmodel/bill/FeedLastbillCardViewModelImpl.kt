package neuro.expenses.register.viewmodel.bill

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.usecase.bill.ObserveLastBillUseCase
import neuro.expenses.register.viewmodel.bill.mapper.BillModelMapper
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider


class FeedLastbillCardViewModelImpl(
  private val observeLastBillUseCase: ObserveLastBillUseCase,
  private val billCardViewModel: IBillCardViewModel,
  private val billModelMapper: BillModelMapper,
  private val schedulerProvider: SchedulerProvider
) : FeedLastbillCardViewModel {
  override fun observe(): Completable {
    return observeLastBillUseCase.observeLastBill().subscribeOn(schedulerProvider.io())
      .observeOn(schedulerProvider.ui()).doOnNext { publish(it) }.ignoreElements()
  }

  private fun publish(billDto: BillDto) {
    val billModel = billModelMapper.map(billDto)
    billCardViewModel.setBillModel(billModel)
  }
}