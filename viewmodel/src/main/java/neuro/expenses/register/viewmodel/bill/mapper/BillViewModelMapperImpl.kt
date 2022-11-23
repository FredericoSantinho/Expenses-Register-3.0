package neuro.expenses.register.viewmodel.bill.mapper

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.bill.model.BillModel

class BillViewModelMapperImpl(private val billModelMapper: BillModelMapper) : BillViewModelMapper {
  override fun map(
    billDto: BillDto, editable: Boolean, opened: Boolean, onLongClick: (BillModel) -> Unit
  ): BillViewModel {
    return BillViewModel(editable, opened, billModelMapper.map(billDto), onLongClick)
  }
}