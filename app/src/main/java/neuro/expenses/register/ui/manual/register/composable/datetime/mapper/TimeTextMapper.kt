package neuro.expenses.register.ui.manual.register.composable.datetime.mapper

interface TimeTextMapper {
  fun map(hour: Int, minute: Int): String
}