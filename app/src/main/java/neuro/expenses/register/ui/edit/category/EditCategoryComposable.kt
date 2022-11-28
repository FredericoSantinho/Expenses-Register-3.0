package neuro.expenses.register.ui.edit.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import neuro.expenses.register.R
import neuro.expenses.register.common.alert.AlertDialogDismissable
import neuro.expenses.register.mocks.category.EditCategoryViewModelMock
import neuro.expenses.register.ui.common.composables.edit.SaveDeleteComposable
import neuro.expenses.register.ui.common.composables.image.AsyncImage
import neuro.expenses.register.ui.common.composables.text.TextFieldWithError
import neuro.expenses.register.ui.common.keyboard.keyboardOptionsText
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.ui.theme.ExpensesRegisterTypography
import neuro.expenses.register.viewmodel.edit.category.EditCategoryUiState
import neuro.expenses.register.viewmodel.edit.category.IEditCategoryViewModel

@Composable
fun EditCategoryComposable(
  editCategoryViewModel: IEditCategoryViewModel, modifier: Modifier = Modifier
) {
  val uiEvent by editCategoryViewModel.uiEvent
  val uiState by editCategoryViewModel.uiState

  val nameIsError = remember { mutableStateOf(false) }
  val nameErrorMessage = remember { mutableStateOf("") }

  Column(
    modifier = modifier
      .background(Color.White)
      .padding(8.dp)
      .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
      text = stringResource(R.string.edit_category_title),
      style = MaterialTheme.typography.h5,
      fontWeight = FontWeight.Bold
    )
    AsyncImage(
      modifier = Modifier
        .size(128.dp)
        .clip(RoundedCornerShape(corner = CornerSize(8.dp))),
      editCategoryViewModel.iconUrl.value
    )
    TextFieldWithError(
      value = editCategoryViewModel.name,
      label = stringResource(R.string.category),
      modifier = Modifier.padding(top = 8.dp),
      keyboardOptions = keyboardOptionsText,
      textStyle = ExpensesRegisterTypography.body2,
      onValueChange = { editCategoryViewModel.onNameChange() },
      isError = nameIsError,
      errorMessage = nameErrorMessage
    )
    TextFieldWithError(
      value = editCategoryViewModel.iconUrl,
      label = stringResource(R.string.icon_url),
      keyboardOptions = keyboardOptionsText,
      textStyle = ExpensesRegisterTypography.body2
    )

    SaveDeleteComposable(
      { editCategoryViewModel.onSaveButton() },
      { editCategoryViewModel.onDeleteButton() },
      modifier = Modifier.padding(top = 16.dp)
    )
  }

  onUiState(uiState, editCategoryViewModel)
  onUiEvent(editCategoryViewModel)
}

@Composable
private fun onUiState(
  uiState: EditCategoryUiState.UiState, editCategoryViewModel: IEditCategoryViewModel
) {
  when (uiState) {
    EditCategoryUiState.UiState.Ready -> {}
    EditCategoryUiState.UiState.ConfirmCategoryDelete -> onConfirmCategoryDelete(
      editCategoryViewModel
    )
    EditCategoryUiState.UiState.CreateCategoryErrorNameConflict -> onCreateCategoryError(
      editCategoryViewModel
    )
    EditCategoryUiState.UiState.UpdateCategoryErrorNameConflict -> onUpdateCategoryError(
      editCategoryViewModel
    )
    EditCategoryUiState.UiState.DeleteCategoryErrorActiveRelations -> onDeleteCategoryError(
      editCategoryViewModel
    )
  }
}

@Composable
fun onCreateCategoryError(editCategoryViewModel: IEditCategoryViewModel) {
  AlertDialogDismissable(
    title = stringResource(
      R.string.edit_category_on_create_category_error_title, editCategoryViewModel.name.value
    ),
    text = stringResource(
      R.string.edit_category_on_create_category_error_text, editCategoryViewModel.name.value
    ),
    confirmButtonText = stringResource(R.string.ok),
    onConfirmButton = { editCategoryViewModel.onCreateCategoryErrorDialogDismiss() },
    onDismissRequest = { editCategoryViewModel.onCreateCategoryErrorDialogDismiss() },
    modifier = Modifier.background(Color.Red)
  )
}

@Composable
fun onUpdateCategoryError(editCategoryViewModel: IEditCategoryViewModel) {
  AlertDialogDismissable(
    title = stringResource(
      R.string.edit_category_on_update_category_error_title, editCategoryViewModel.currentName
    ),
    text = stringResource(
      R.string.edit_category_on_update_category_error_text,
      editCategoryViewModel.currentName,
      editCategoryViewModel.name.value
    ),
    confirmButtonText = stringResource(R.string.ok),
    onConfirmButton = { editCategoryViewModel.onUpdateCategoryErrorDialogDismiss() },
    onDismissRequest = { editCategoryViewModel.onUpdateCategoryErrorDialogDismiss() },
    modifier = Modifier.background(Color.Red)
  )
}

@Composable
fun onConfirmCategoryDelete(editCategoryViewModel: IEditCategoryViewModel) {
  AlertDialogDismissable(
    title = stringResource(
      R.string.edit_category_confirm_delete_title,
      editCategoryViewModel.currentName
    ),
    confirmButtonText = stringResource(R.string.yes),
    dismissButtonText = stringResource(R.string.no),
    onConfirmButton = { editCategoryViewModel.onConfirmDelete() },
    onDismissRequest = { editCategoryViewModel.onConfirmDeleteDismiss() })
}

@Composable
fun onDeleteCategoryError(editCategoryViewModel: IEditCategoryViewModel) {
  AlertDialogDismissable(
    title = stringResource(
      R.string.edit_category_on_delete_category_error_title, editCategoryViewModel.currentName
    ),
    text = stringResource(
      R.string.edit_category_on_delete_category_error_text, editCategoryViewModel.currentName
    ),
    confirmButtonText = stringResource(R.string.ok),
    onConfirmButton = { editCategoryViewModel.onDeleteCategoryErrorDialogDismiss() },
    onDismissRequest = { editCategoryViewModel.onDeleteCategoryErrorDialogDismiss() },
    modifier = Modifier.background(Color.Red)
  )
}

@Composable
private fun onUiEvent(
  editCategoryViewModel: IEditCategoryViewModel
) {
  editCategoryViewModel.eventConsumed()
}


@Preview
@Composable
fun PreviewEditCategoryComposable() {
  val editCategoryViewModel = EditCategoryViewModelMock()

  ExpensesRegisterTheme {
    EditCategoryComposable(editCategoryViewModel)
  }
}
