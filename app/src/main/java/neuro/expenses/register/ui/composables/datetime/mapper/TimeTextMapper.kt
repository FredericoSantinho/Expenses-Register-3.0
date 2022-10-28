package neuro.expenses.register.ui.composables.datetime.mapper

interface TimeTextMapper {
  fun map(hour: Int, minute: Int): String
}