package neuro.expenses.register.ui.composables.datetime.mapper

interface DateTextMapper {
  fun map(day: Int, month: Int, year: Int): String
}