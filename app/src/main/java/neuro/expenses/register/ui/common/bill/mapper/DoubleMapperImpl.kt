package neuro.expenses.register.ui.common.bill.mapper

class DoubleMapperImpl : DoubleMapper {
  override fun map(double: Double): String {
    return double.toString()
  }
}