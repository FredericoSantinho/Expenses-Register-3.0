package neuro.expenses.register.ui.manual.register.mapper

import androidx.annotation.StringRes
import neuro.expenses.register.R
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiState.Message
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiState.Message.*

@StringRes
fun Message.toPresentation(): Int = when (this) {
  EMPTY_DESCRIPTION -> R.string.manual_register_error_empty_description
  EMPTY_PLACE -> R.string.manual_register_error_empty_place
  INVALID_AMOUNT -> R.string.manual_register_error_invalid_amount
}
