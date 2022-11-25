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
import neuro.expenses.register.ui.common.composables.edit.SaveDeleteComposable
import neuro.expenses.register.ui.common.composables.image.AsyncImage
import neuro.expenses.register.ui.common.composables.text.TextFieldWithError
import neuro.expenses.register.ui.common.keyboard.keyboardOptionsText
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.ui.theme.ExpensesRegisterTypography
import neuro.expenses.register.viewmodel.edit.category.EditCategoryViewModel

@Composable
fun EditCategoryComposable(
  editCategoryViewModel: EditCategoryViewModel, modifier: Modifier = Modifier
) {
  val descriptionIsError = remember { mutableStateOf(false) }
  val descriptionErrorMessage = remember { mutableStateOf("") }

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
      modifier = Modifier.padding(top = 8.dp),
      label = stringResource(R.string.category),
      keyboardOptions = keyboardOptionsText,
      value = editCategoryViewModel.name,
      isError = descriptionIsError,
      errorMessage = descriptionErrorMessage,
      onValueChange = { editCategoryViewModel.onNameChange() },
      textStyle = ExpensesRegisterTypography.body2
    )
    TextFieldWithError(
      label = stringResource(R.string.icon_url),
      keyboardOptions = keyboardOptionsText,
      value = editCategoryViewModel.iconUrl,
      textStyle = ExpensesRegisterTypography.body2
    )

    SaveDeleteComposable({}, {}, modifier = Modifier.padding(top = 16.dp))
  }
}

@Preview
@Composable
fun PreviewEditCategoryComposable() {
  val editCategoryViewModel = EditCategoryViewModel()
  editCategoryViewModel.name.value = "Super"

  ExpensesRegisterTheme {
    EditCategoryComposable(editCategoryViewModel)
  }
}
