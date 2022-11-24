package neuro.expenses.register.ui.edit.placeproduct

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import neuro.expenses.register.R
import neuro.expenses.register.ui.common.composables.text.TextFieldWithDropdown
import neuro.expenses.register.ui.common.keyboard.keyboardOptionsText
import neuro.expenses.register.ui.home.composable.ProductsListComposable
import neuro.expenses.register.ui.theme.ExpensesRegisterTypography
import neuro.expenses.register.viewmodel.edit.placeproduct.EditPlaceProductsUiState.UiState
import neuro.expenses.register.viewmodel.edit.placeproduct.EditPlaceProductsViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditPlaceProductsComposable(editPlaceProductsViewModel: EditPlaceProductsViewModel = getViewModel()) {
  val uiState by editPlaceProductsViewModel.uiState

  val focusManager = LocalFocusManager.current
  val modalBottomSheetInitialValue = remember {
    if (uiState == UiState.Editing) ModalBottomSheetValue.Expanded else ModalBottomSheetValue.Hidden
  }
  val modalBottomSheetState = rememberModalBottomSheetState(
    modalBottomSheetInitialValue, confirmStateChange = {
      it != ModalBottomSheetValue.HalfExpanded
    }, skipHalfExpanded = true
  )

  Column(Modifier.fillMaxSize()) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
      TextFieldWithDropdown(
        modifier = Modifier.width(300.dp),
        dataIn = editPlaceProductsViewModel.placesNames.subscribeAsState(initial = emptyList()),
        label = stringResource(R.string.place),
        keyboardOptions = keyboardOptionsText,
        value = editPlaceProductsViewModel.place,
        textStyle = ExpensesRegisterTypography.body2,
        onValueChange = { },
        onSelectOption = {
          focusManager.clearFocus()
          editPlaceProductsViewModel.onPlaceValueChange()
        }
      )
    }
    ProductsListComposable(editPlaceProductsViewModel.productsListViewModel)
  }
  ModalBottomSheetLayout(sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    sheetBackgroundColor = Color.Transparent,
    sheetState = modalBottomSheetState,
    sheetContent = {
      EditPlaceProductComposable(editPlaceProductsViewModel.editPlaceProductViewModel)
    }) {}
}