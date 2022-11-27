package neuro.expenses.register.viewmodel.edit.category

import neuro.expenses.register.viewmodel.common.BaseUiEvent
import neuro.expenses.register.viewmodel.edit.category.EditCategoriesUiEvent.UiEvent

class EditCategoriesUiEvent() : BaseUiEvent<UiEvent>() {
  fun openEditCategory() {
    _uiEvent.value = UiEvent.OpenEditCategory()
  }

  fun closeEditPlaceProduct() {
    _uiEvent.value = UiEvent.CloseEditCategory()
  }

  sealed class UiEvent {
    class OpenEditCategory : UiEvent()
    class CloseEditCategory : UiEvent()
  }
}
