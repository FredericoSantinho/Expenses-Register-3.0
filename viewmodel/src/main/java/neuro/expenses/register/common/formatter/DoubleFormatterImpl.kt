package neuro.expenses.register.common.formatter

class DoubleFormatterImpl(private val decimalFormatter: DecimalFormatter) : DoubleFormatter {
  override fun format(double: Double): String {
    return decimalFormatter.format(double)
  }
}