package neuro.expenses.register.common.formatter

class NumberFormaterImpl(val s: String = "%02d") : NumberFormater {
  override fun format(n: Int): String {
    return s.format(n)
  }
}