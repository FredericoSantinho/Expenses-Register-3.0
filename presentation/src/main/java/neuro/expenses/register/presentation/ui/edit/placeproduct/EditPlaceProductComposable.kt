package neuro.expenses.register.presentation.ui.edit.placeproduct

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import neuro.expenses.register.presentation.R
import neuro.expenses.register.presentation.ui.common.composables.edit.SaveDeleteComposable
import neuro.expenses.register.presentation.ui.common.composables.image.AsyncImage
import neuro.expenses.register.presentation.ui.common.composables.text.CurrencyTextField
import neuro.expenses.register.presentation.ui.common.composables.text.TextFieldWithDropdown
import neuro.expenses.register.presentation.ui.common.composables.text.TextFieldWithError
import neuro.expenses.register.presentation.ui.common.keyboard.keyboardOptionsText
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTypography
import neuro.expenses.register.viewmodel.edit.placeproduct.EditPlaceProductViewModel

@Composable
fun EditPlaceProductComposable(
  editPlaceProductViewModel: EditPlaceProductViewModel,
  modifier: Modifier = Modifier
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
      modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
      text = stringResource(R.string.edit_place_product_title),
      style = MaterialTheme.typography.h5,
      fontWeight = FontWeight.Bold
    )
    AsyncImage(
      modifier = Modifier
        .testTag(editPlaceProductViewModel.iconUrl.value)
        .size(128.dp)
        .clip(RoundedCornerShape(corner = CornerSize(8.dp))),
      editPlaceProductViewModel.iconUrl.value
    )
    TextFieldWithError(
      modifier = Modifier.fillMaxWidth(),
      value = editPlaceProductViewModel.description,
      label = stringResource(R.string.description),
      keyboardOptions = keyboardOptionsText,
      textStyle = ExpensesRegisterTypography.body2,
      onValueChange = { editPlaceProductViewModel.onDescriptionChange() },
      isError = descriptionIsError,
      errorMessage = descriptionErrorMessage
    )
    Row {
      TextFieldWithDropdown(
        modifier = Modifier.weight(1f),
        dataIn = editPlaceProductViewModel.categoriesNames.subscribeAsState(initial = emptyList()),
        label = stringResource(R.string.category),
        keyboardOptions = keyboardOptionsText,
        onValueChange = { editPlaceProductViewModel.onCategoryChange() },
        value = editPlaceProductViewModel.category,
        isError = categoryIsError,
        textStyle = ExpensesRegisterTypography.body2,
        onSelectOption = { focusManager.moveFocus(FocusDirection.Next) }
      )
      Divider(modifier = Modifier.weight(0.05f))
      CurrencyTextField(
        value = editPlaceProductViewModel.price,
        label = stringResource(R.string.price),
        modifier = Modifier.widthIn(max = 96.dp),
        textStyle = ExpensesRegisterTypography.body2
      )
    }
    TextFieldWithError(
      value = editPlaceProductViewModel.iconUrl,
      label = stringResource(R.string.icon_url),
      keyboardOptions = keyboardOptionsText,
      textStyle = ExpensesRegisterTypography.body2
    )
    SaveDeleteComposable(
      { editPlaceProductViewModel.onSaveButton() },
      { editPlaceProductViewModel.onDeleteButton() })
  }
}