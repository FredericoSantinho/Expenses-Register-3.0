package neuro.expenses.register.viewmodel.bill.mapper

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.viewmodel.bill.BillViewModel

class BillViewModelMapperImpl(private val billModelMapper: BillModelMapper) : BillViewModelMapper {
  override fun map(
    billDto: BillDto, editable: Boolean, opened: Boolean, onBillLongClick: (Long) -> Unit
  ): BillViewModel {
    return BillViewModel(opened, billModelMapper.map(billDto), onBillLongClick)
  }
}