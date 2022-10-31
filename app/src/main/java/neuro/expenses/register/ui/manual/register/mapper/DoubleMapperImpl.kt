package neuro.expenses.register.ui.manual.register.mapper

class DoubleMapperImpl : DoubleMapper {
  override fun map(double: Double): String {
    return double.toString()
  }
}