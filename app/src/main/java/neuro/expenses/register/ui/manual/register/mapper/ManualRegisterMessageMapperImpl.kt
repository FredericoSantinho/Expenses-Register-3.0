package neuro.expenses.register.ui.manual.register.mapper

import androidx.annotation.StringRes
import neuro.expenses.register.R
import neuro.expenses.register.viewmodel.manual.register.Message
import neuro.expenses.register.viewmodel.manual.register.Message.*

interface ManualRegisterMessageMapper {
  @StringRes
  fun map(message: Message): Int
}

class ManualRegisterMessageMapperImpl : ManualRegisterMessageMapper {
  @StringRes
  override fun map(message: Message): Int {
    return when (message) {
      EMPTY_DESCRIPTION -> R.string.manual_register_empty_description
      EMPTY_PLACE -> R.string.manual_register_empty_place
      INVALID_AMOUNT -> R.string.manual_register_invalid_amount
    }
  }
}