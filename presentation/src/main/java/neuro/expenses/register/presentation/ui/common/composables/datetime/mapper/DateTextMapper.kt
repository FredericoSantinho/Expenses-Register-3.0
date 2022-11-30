package neuro.expenses.register.presentation.ui.common.composables.datetime.mapper

interface DateTextMapper {
  fun map(day: Int, month: Int, year: Int): String
}