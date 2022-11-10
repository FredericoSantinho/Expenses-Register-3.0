package neuro.expenses.register.viewmodel.bill.mapper

import neuro.expenses.register.common.formatter.DoubleFormatter
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.viewmodel.bill.model.BillModel

class BillModelMapperImpl(
  private val dateTimeMapper: DateTimeMapper,
  private val doubleFormatter: DoubleFormatter
) : BillModelMapper {
  override fun map(billDto: BillDto): BillModel {
    val time = dateTimeMapper.mapTime(billDto.calendar)
    val date = dateTimeMapper.mapDate(billDto.calendar)
    val total = doubleFormatter.format(billDto.total)

    return BillModel(billDto.id, billDto.iconUrl, billDto.place, time, date, total, billDto.isOpen)
  }
}