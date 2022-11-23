package neuro.expenses.register.ui.edit.placeproduct

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import neuro.expenses.register.R
import neuro.expenses.register.ui.common.composables.edit.SaveDeleteComposable
import neuro.expenses.register.ui.common.composables.image.AsyncImage
import neuro.expenses.register.ui.common.composables.text.CurrencyTextField
import neuro.expenses.register.ui.common.composables.text.TextFieldWithDropdown
import neuro.expenses.register.ui.common.composables.text.TextFieldWithError
import neuro.expenses.register.ui.common.keyboard.keyboardOptionsText
import neuro.expenses.register.ui.theme.ExpensesRegisterTypography
import neuro.expenses.register.viewmodel.edit.placeproduct.EditPlaceProductViewModel

@Composable
fun EditPlaceProductComposable(
  editPlaceProductViewModel: EditPlaceProductViewModel, modifier: Modifier = Modifier
) {
  val descriptionIsError = remember { mutableStateOf(false) }
  val descriptionErrorMessage = remember { mutableStateOf("") }
  val categoryIsError = remember { mutableStateOf(false) }

  val focusManager = LocalFocusManager.current

  Column(
    modifier = modifier
      .background(Color.White)
      .padding(8.dp)
      .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      modifier = Modifier.padding(bottom = 16.dp),
      text = stringResource(R.string.edit_place_product_title),
      style = MaterialTheme.typography.h5,
      fontWeight = FontWeight.Bold
    )
    AsyncImage(modifier = Modifier
      .semantics { testTag = editPlaceProductViewModel.iconUrl.value }
      .size(128.dp)
      .clip(RoundedCornerShape(corner = CornerSize(8.dp))),
      editPlaceProductViewModel.iconUrl.value)
    TextFieldWithError(
      label = stringResource(R.string.description),
      keyboardOptions = keyboardOptionsText,
      value = editPlaceProductViewModel.description,
      isError = descriptionIsError,
      errorMessage = descriptionErrorMessage,
      onValueChange = { editPlaceProductViewModel.onDescriptionChange() },
      textStyle = ExpensesRegisterTypography.body2
    )
    TextFieldWithDropdown(
      dataIn = editPlaceProductViewModel.categoriesNames.subscribeAsState(initial = emptyList()),
      label = stringResource(R.string.category),
      keyboardOptions = keyboardOptionsText,
      onValueChange = { editPlaceProductViewModel.onCategoryChange() },
      value = editPlaceProductViewModel.category,
      isError = categoryIsError,
      textStyle = ExpensesRegisterTypography.body2,
      onSelectOption = { focusManager.moveFocus(FocusDirection.Next) }
    )
    TextFieldWithError(
      label = stringResource(R.string.icon_url),
      keyboardOptions = keyboardOptionsText,
      value = editPlaceProductViewModel.iconUrl,
      textStyle = ExpensesRegisterTypography.body2
    )
    CurrencyTextField(
      modifier = Modifier.width(96.dp),
      label = stringResource(R.string.price),
      value = editPlaceProductViewModel.price,
      textStyle = ExpensesRegisterTypography.body2
    )
    SaveDeleteComposable(
      { editPlaceProductViewModel.onSaveButton() },
      { editPlaceProductViewModel.onDeleteButton() })
  }
}