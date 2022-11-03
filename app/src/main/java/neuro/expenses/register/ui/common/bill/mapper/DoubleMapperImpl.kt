package neuro.expenses.register.ui.common.bill.mapper

import neuro.expenses.register.common.formatter.DecimalFormatter

class DoubleMapperImpl(private val decimalFormatter: DecimalFormatter) : DoubleMapper {
  override fun map(double: Double): String {
    return decimalFormatter.format(double)
  }
}