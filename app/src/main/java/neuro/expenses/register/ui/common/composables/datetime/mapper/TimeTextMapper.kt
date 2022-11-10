package neuro.expenses.register.ui.common.composables.datetime.mapper

interface TimeTextMapper {
  fun map(hour: Int, minute: Int): String
}