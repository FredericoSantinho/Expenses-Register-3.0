package neuro.expenses.register.ui.manual.register.composable.datetime.mapper

interface DateTextMapper {
  fun map(day: Int, month: Int, year: Int): String
}