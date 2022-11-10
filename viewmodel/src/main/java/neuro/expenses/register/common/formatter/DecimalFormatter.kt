package neuro.expenses.register.common.formatter

fun interface DecimalFormatter {
  fun format(value: Double): String
}