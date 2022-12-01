package neuro.expenses.register.viewmodel.bill.mapper

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.viewmodel.bill.BillCardViewModel
import neuro.expenses.register.viewmodel.bill.IBillCardViewModel

class billCardViewModelMapperImpl(private val billModelMapper: BillModelMapper) :
  billCardViewModelMapper {
  override fun map(
    billDto: BillDto, editable: Boolean, opened: Boolean, onBillLongClick: (Long) -> Unit
  ): IBillCardViewModel {
    return BillCardViewModel(opened, billModelMapper.map(billDto), onBillLongClick)
  }
}