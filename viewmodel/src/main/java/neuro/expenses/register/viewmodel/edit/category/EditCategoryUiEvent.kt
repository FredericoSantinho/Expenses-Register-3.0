package neuro.expenses.register.viewmodel.edit.category

import neuro.expenses.register.viewmodel.common.BaseUiEvent
import neuro.expenses.register.viewmodel.edit.category.EditCategoryUiEvent.UiEvent

class EditCategoryUiEvent : BaseUiEvent<UiEvent>() {
  sealed class UiEvent
}