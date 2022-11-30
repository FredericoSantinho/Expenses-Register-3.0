package neuro.expenses.register.presentation.ui.common.composables.appbar.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import neuro.expenses.register.presentation.R
import neuro.expenses.register.viewmodel.appbar.Title
import neuro.expenses.register.viewmodel.appbar.Title.EmptyTitle
import neuro.expenses.register.viewmodel.bills.BillsTitle
import neuro.expenses.register.viewmodel.edit.EditTitle
import neuro.expenses.register.viewmodel.home.HomeTitle
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterTitle

@Composable
fun Title.toPresentation(): String {
  return when (this) {
    is EmptyTitle -> ""
    is HomeTitle -> stringResource(R.string.home_title)
    is ManualRegisterTitle -> stringResource(R.string.manual_register_title)
    is BillsTitle -> stringResource(R.string.bills_title)
    is EditTitle -> stringResource(R.string.edit_title)
    else -> {
      throw java.lang.IllegalArgumentException("Mapping not implemented for class ${this.javaClass}!")
    }
  }
}