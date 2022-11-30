package neuro.expenses.register.presentation.ui.common.composables.datetime.mapper

interface TimeTextMapper {
  fun map(hour: Int, minute: Int): String
}