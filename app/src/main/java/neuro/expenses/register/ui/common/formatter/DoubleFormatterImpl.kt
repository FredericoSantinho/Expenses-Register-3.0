package neuro.expenses.register.ui.common.formatter

import neuro.expenses.register.common.formatter.DecimalFormatter

class DoubleFormatterImpl(private val decimalFormatter: DecimalFormatter) : DoubleFormatter {
  override fun format(double: Double): String {
    return decimalFormatter.format(double)
  }
}