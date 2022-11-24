package neuro.expenses.register.viewmodel.common.formatter

class CurrencyFormatterImpl(
  private val decimalFormatter: DecimalFormatter,
  private val currency: String,
) : CurrencyFormatter {
  override fun format(value: Double): String {
    return decimalFormatter.format(value) + currencySuffix()
  }

  override fun format(string: String): String {
    return string + currencySuffix()
  }

  private fun currencySuffix() = " $currency"
}