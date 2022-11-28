package neuro.expenses.register.ui.edit.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import neuro.expenses.register.common.compose.rememberUnit
import neuro.expenses.register.mocks.category.EditCategoriesViewModelMock
import neuro.expenses.register.ui.home.composable.*
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.ui.theme.grey_fog_lighter
import neuro.expenses.register.viewmodel.edit.category.EditCategoriesUiEvent.UiEvent
import neuro.expenses.register.viewmodel.edit.category.EditCategoriesViewModel
import neuro.expenses.register.viewmodel.edit.category.IEditCategoriesViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditCategoriesComposable(
  editCategoriesViewModel: IEditCategoriesViewModel = getViewModel<EditCategoriesViewModel>(),
  navController: NavHostController? = null
) {
  rememberUnit { editCategoriesViewModel.onComposition() }

  val uiEvent = editCategoriesViewModel.uiEvent

  val coroutineScope = rememberCoroutineScope()
  val modalBottomSheetState =
    rememberModalBottomSheetState(if (editCategoriesViewModel.modalBottomSheetVisible.value) ModalBottomSheetValue.Expanded else ModalBottomSheetValue.Hidden)
  val categories = editCategoriesViewModel.categories.subscribeAsState(initial = emptyList())

  ModalBottomSheetLayout(
    modalBottomSheetState,
    onModalBottomSheetVisible = { editCategoriesViewModel.onModalBottomSheetVisible() },
    onModalBottomSheetNotVisible = { editCategoriesViewModel.onModalBottomSheetNotVisible() },
    modalContent = { EditCategoryComposable(editCategoriesViewModel.editCategoryViewModel) }) {
    Column(
      modifier = Modifier.background(color = grey_fog_lighter)
    ) {
      LazyVerticalGrid(
        modifier = Modifier
          .padding(4.dp)
          .fillMaxSize(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        items(categories.value, key = {
          it.id
        }) { categoryModel ->
          CategoryComposable(categoryModel) { editCategoriesViewModel.onCategoryClick(it) }
        }
      }
    }
  }
  onUiEvent(uiEvent, coroutineScope, modalBottomSheetState, editCategoriesViewModel)

  navController?.let { addBackHandler(modalBottomSheetState, coroutineScope, navController) }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun onUiEvent(
  uiEvent: State<UiEvent?>,
  coroutineScope: CoroutineScope,
  modalBottomSheetState: ModalBottomSheetState,
  editCategoriesViewModel: IEditCategoriesViewModel
) {
  when (uiEvent.value) {
    is UiEvent.OpenEditCategory -> showModalBottomSheet(
      uiEvent.value, coroutineScope, modalBottomSheetState
    )
    is UiEvent.CloseEditCategory -> hideModalBottomSheet(
      uiEvent.value, coroutineScope, modalBottomSheetState
    )
    null -> {}
  }
  editCategoriesViewModel.eventConsumed()
}

@Preview
@Composable
fun PreviewEditCategoriesComposable() {
  ExpensesRegisterTheme {
    EditCategoriesComposable(EditCategoriesViewModelMock())
  }
}