package neuro.expenses.register.viewmodel.bill.mapper

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.viewmodel.bill.model.BillModel
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatter

class BillModelMapperImpl(
  private val dateTimeMapper: DateTimeMapper,
  private val decimalFormatter: DecimalFormatter
) : BillModelMapper {
  override fun map(billDto: BillDto): BillModel {
    val time = dateTimeMapper.mapTime(billDto.calendar)
    val date = dateTimeMapper.mapDate(billDto.calendar)
    val total = decimalFormatter.format(billDto.total)

    return BillModel(
      billDto.id,
      billDto.iconUrl,
      billDto.place.name,
      time,
      date,
      total,
      billDto.isOpen
    )
  }
}